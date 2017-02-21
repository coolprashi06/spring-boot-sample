package com.prashast.rest;


import com.prashast.dto.User;
import com.prashast.service.SecuredRestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/secured/")
public class SecuredRest {

    @Autowired
    SecuredRestService securedRestService;

    static Logger log = Logger.getLogger(SecuredRest.class.getName());

    @RequestMapping(value = "info", produces = {MediaType.TEXT_PLAIN_VALUE}, method = RequestMethod.GET)
    public String getSecuredContent(){
        return "hey, you're authenticated with user role";
    }

    @RequestMapping("hello")
    public ModelAndView helloUser(@RequestParam("name") String name){
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject("name",name);
        return modelAndView;
    }

    @RequestMapping(value = "getUserByLastName", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUser(@RequestParam String lastName){
        List<User> users = securedRestService.findUsersByLastName(lastName);
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
}
