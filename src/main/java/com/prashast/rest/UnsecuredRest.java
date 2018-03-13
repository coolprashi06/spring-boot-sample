package com.prashast.rest;

import com.prashast.exception.CustomException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UnsecuredRest.UNSECURED_PATH)
@CrossOrigin("*")
public class UnsecuredRest {

    public final static String UNSECURED_PATH = "/unsecured";

    @RequestMapping
    public String unsecuredResource(){
        return "hello, this is unsecured resource";
    }

    @RequestMapping("/data/{index}")
    public String unsecuredData(@PathVariable("index") int index) throws Exception{
        if(index == 1){
            return "this is valid data";
        }else if(index == 2){
            throw new CustomException("this is invalid index");
        }else if(index == 3){
            throw new TypeMismatchException(1, Integer.class);
        }else{
            throw new HttpMediaTypeNotSupportedException("not supported exception");
        }
    }

}
