package com.papillon.dc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

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

            System.out.println(offerDAO.getOffer(2));
        }catch (CannotGetJdbcConnectionException e){ //happens due to unauthorized password
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
        }catch (DataAccessException e){ //happens due to wrong sql grammar
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
        }

    }
}
