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
            log.info("answer...");

            int count=0;
            String str = br.readLine();
            while(str != null  && count <= 7){
                if (str.length() == 0){
                    count ++;
                }else {
                    count=0;
                }
                str = br.readLine();
                log.info(str);
                response +=str;
            }
/*            while((str = br.readLine()) != null){
            }*/
//            response = br.readLine();
            log.info("end**************************");
        }catch (Exception e){
            throw e;
        }
        return response;
    }
}