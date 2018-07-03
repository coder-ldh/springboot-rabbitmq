package com.ldh.util;

import com.ldh.entity.TextTry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.concurrent.Callable;

/**
 * Created by ldh on 2018/7/2.
 */
public class TextTryTask implements Callable<String> {

    private static final Logger log = LoggerFactory.getLogger(TextTryTask.class);

    private TextTry textTry;

    private String sayWord;


    public TextTryTask(TextTry textTry, String sayWord) {
        this.textTry = textTry;
        this.sayWord = sayWord;
    }

    @Override
    public String call() throws Exception {
        String response = "";
        BufferedWriter bw = textTry.getBufferedWriter();
        BufferedReader br = textTry.getBufferedReader();
        try {
            log.info("[request]——>[" + sayWord+ "]");
            bw.write(sayWord + "\n");
            bw.flush();
            /*String str = null;
                log.info("[br.ready()]——>[" + br.ready()+ "]");
                log.info("[str = br.readLine()]——>[" + ((str = br.readLine())!= null)+ "]");
            while (br.ready() && (str = br.readLine())!= null ){*/
             response += br.readLine();
            //}
            log.info("[response]——>[" + response+ "]");
        }catch (Exception e){
            throw e;
        }
        return response;
    }
}