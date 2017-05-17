package com.papillon.dc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by papillon on 5/16/2017.
 */
@Component("OfferDAO")
public class OfferDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    @Qualifier(value = "data_source")
    public void setJdbcTemplate(javax.sql.DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    /*****************************************************
     **********CRUD OPERATIONS****************************
     *****************************************************/

    public boolean create(Offer offer){
        BeanPropertySqlParameterSource beanPropertySqlParameterSource =
                new BeanPropertySqlParameterSource(offer);
        return namedParameterJdbcTemplate.update("insert into offers(name,text,email) values (:name,:text,:email)",
                beanPropertySqlParameterSource) == 1;
    }

    @Transactional
    public int[] create(List<Offer> offers){
        SqlParameterSource[] source = SqlParameterSourceUtils.createBatch(offers.toArray());
        return namedParameterJdbcTemplate.batchUpdate("insert into offers(id,name,text,email) values (:id,:name,:text,:email)",
                source);
    }

    public List<Offer> getOffers(){

        MapSqlParameterSource parameterSource =
                new MapSqlParameterSource();
        parameterSource.addValue("name","Sue");

        return namedParameterJdbcTemplate.query("select * from offers",
                //parameterSource,
                new RowMapper<Offer>() {
                    @Override
                    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Offer offer = new Offer();
                        offer.setId(rs.getInt("id"));
                        offer.setName(rs.getString("name"));
                        offer.setText(rs.getString("text"));
                        offer.setEmail(rs.getString("email"));
                        return offer;
                    }
                });
    }

    public Offer getOffer(int id){

        MapSqlParameterSource parameterSource =
                new MapSqlParameterSource();
        parameterSource.addValue("id", id);

        return namedParameterJdbcTemplate.queryForObject("select * from offers where id = :id",
                parameterSource,
                new RowMapper<Offer>() {
                    @Override
                    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Offer offer = new Offer();
                        offer.setId(rs.getInt("id"));
                        offer.setName(rs.getString("name"));
                        offer.setText(rs.getString("text"));
                        offer.setEmail(rs.getString("email"));
                        return offer;
                    }
                });
    }

    public boolean update(int id,String newName){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        parameterSource.addValue("newname",newName);
        return namedParameterJdbcTemplate.update("update offers set name = :newname where id = :id",parameterSource) == 1;
    }

    public boolean delete(int id){
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id",id);
        return namedParameterJdbcTemplate.update("delete from offers where id = :id",parameterSource) == 1;
    }



}
