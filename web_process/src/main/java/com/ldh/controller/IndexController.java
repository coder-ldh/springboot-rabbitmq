package com.ldh.controller;

import com.ldh.service.TestTryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ldh on 2018/7/2.
 */
@RestController
public class IndexController {

    private static final Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TestTryService testTryService;

    @RequestMapping(value = "/index")
    public String index(String textTryId,String sayWord ){
        try {
            String response = testTryService.interacts(textTryId,sayWord);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            log.error("e" + e);
            return "error";
        }
    }
}