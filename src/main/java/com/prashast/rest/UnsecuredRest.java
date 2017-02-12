package com.prashast.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(UnsecuredRest.UNSECURED_PATH)
@CrossOrigin("*")
public class UnsecuredRest {

    public final static String UNSECURED_PATH = "/unsecured";

    @RequestMapping("/hello/{name}")
    public String helloWorld(@PathVariable("name") String name){
        return "hello, "+ name + "!.";
    }

}
