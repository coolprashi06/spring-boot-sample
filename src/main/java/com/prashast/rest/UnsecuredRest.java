package com.prashast.rest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.prashast.exception.CustomException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

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

    /**
     * this is just to show how to stream data in continous fashion
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/data/stream", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseBodyEmitter getStreamingData() throws Exception{
        ResponseBodyEmitter emitter = new ResponseBodyEmitter(Long.valueOf(30000));

        ObjectMapper mapper = new ObjectMapper();


        for(int i = 0 ; i < 30000; i++){
            ObjectNode node = mapper.createObjectNode();
            node.put("val", i);

            String data = mapper.writeValueAsString(node);
            emitter.send(data, MediaType.APPLICATION_JSON);

        }

        emitter.complete();

        return emitter;
    }

}
