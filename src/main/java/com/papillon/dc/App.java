package com.papillon.dc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by papillon on 5/15/2017.
 */
public class App {

    public static void main(String[] args){
        ApplicationContext context =
                new FileSystemXmlApplicationContext("ApplicationContext.xml");

        /*Robot robot = (Robot)context.getBean("robot");
        robot.speak();*/

        OfferDAO offerDAO = (OfferDAO)context.getBean("OfferDAO");
        for(Offer offer : offerDAO.getOffers()){
            System.out.println(offer);
        }
    }
}
