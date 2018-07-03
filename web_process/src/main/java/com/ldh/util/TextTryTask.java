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
        BufferedWriter bufferedWriter = textTry.getBufferedWriter();
            BufferedReader br = textTry.getBufferedReader();
        try {
            log.info("saying...");
            bufferedWriter.write(sayWord + "\n");
            bufferedWriter.flush();
           bufferedWriter.close();
            log.info("answer...");
            String str;
            while((str = br.readLine()) != null){
                response = response + br.readLine();
            }
            log.info("end**************************");
        }catch (Exception e){
            throw e;
        }
        return response;
    }
}