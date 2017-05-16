package com.papillon.dc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by papillon on 5/16/2017.
 */
@Component("OfferDAO")
public class OfferDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(javax.sql.DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<Offer> getOffers(){
        return jdbcTemplate.query("select * from offers", new RowMapper<Offer>() {
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
}
