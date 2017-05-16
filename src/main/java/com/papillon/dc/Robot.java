package com.papillon.dc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by papillon on 5/15/2017.
 */
@Component("robot")
public class Robot {

    private int id = 0;
    private String speech = "hello";

    public void speak(){
        System.out.println(id+" : "+speech);
    }

    @Autowired
    public void setId(@Value("#{random.getText()?.length()}") int id){
        this.id = id;
    }

    @Autowired
    //expressions within #{}
    //new java.util.Date().toString()
    //NOT Math.PI BUT T(Math).PI ---> for static methods,prefix with 'T'
    public void setSpeech(@Value("#{T(Math).PI}") String speech){
        this.speech = speech;
    }
}
