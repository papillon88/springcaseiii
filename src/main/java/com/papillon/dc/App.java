package com.papillon.dc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by papillon on 5/15/2017.
 */
public class App {

    public static void main(String[] args){
        ApplicationContext context =
                new FileSystemXmlApplicationContext("ApplicationContext.xml");

        /*Robot robot = (Robot)context.getBean("robot");
        robot.speak();*/
        try {
            OfferDAO offerDAO = (OfferDAO)context.getBean("OfferDAO");
            for(Offer offer : offerDAO.getOffers()){
                System.out.println(offer);
            }
            //System.out.println(offerDAO.getOffer(2));
            System.out.println(offerDAO.delete(2));
            offerDAO.create(new Offer("debas","debas@gmail.com","dummy1"));
            offerDAO.create(new Offer("devo","devo@gmail.com","dummy2"));
            for(Offer offer : offerDAO.getOffers()){
                System.out.println(offer);
            }
            offerDAO.update(5,"sibu");
            for(Offer offer : offerDAO.getOffers()){
                System.out.println(offer);
            }
            List<Offer> offers = new ArrayList<>();
            offers.add(new Offer("khushi","khushi@gmail.com","dummy3"));
            offers.add(new Offer("momo","momo@gmail.com","dummy4"));
            offers.add(new Offer("english","english@gmail.com","dummy5"));
            offerDAO.create(offers);
            for(Offer offer : offerDAO.getOffers()){
                System.out.println(offer);
            }
        }catch (CannotGetJdbcConnectionException e){ //happens due to unauthorized password
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
        }catch (DataAccessException e){ //happens due to wrong sql grammar
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
        }

    }
}
