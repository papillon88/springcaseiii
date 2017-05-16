package com.papillon.dc;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

/**
 * Created by papillon on 5/15/2017.
 */
@Component("random")
public class RandomText {

    private static String[] text = {
            "I'll be back",
            "get out",
            "I want your clothes,boots and motorcycle"
    };

    public String getText(){
        SecureRandom random = new SecureRandom();
        return text[random.nextInt(text.length)];
    }
}
