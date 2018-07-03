package com.ldh.service;

import com.ldh.constant.TextTryConstant;
import com.ldh.entity.TextTry;
import com.ldh.config.TextTryThreadConfig;
import com.ldh.util.TextTryTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.FutureTask;

/**
 * Created by ldh on 2018/7/2.
 */
@Service
public class TextTryServiceImpl implements  TestTryService {

    private static final Logger log = LoggerFactory.getLogger(TextTryServiceImpl.class);

    @Autowired
    private TextTryThreadConfig textTryThreadConfig;

    @Autowired
    private TextTry textTry;

    @Override
    public String interacts(String textTryId, String sayWord) throws Exception{
        try {
            log.info("[sayWord]——>" + sayWord + "[textTryId]——>" + textTryId);
            ConcurrentHashMap<String, TextTry> cmp = TextTryConstant.getTEXTTRYS();
            for (String key : cmp.keySet()) {
                TextTry value = cmp.get(key);
                log.info("Key = " + key + ", Value = " + value.toString());
            }


            TextTry ty = cmp.get(textTryId);
            log.info("[ty]——>" + ty);
            if (ty == null){
                ty = textTry.setup();
                log.info("[ty]——>" + ty.toString());
                log.info("[ty]——>" + sayWord + "[textTryId]——>" + textTryId);
                cmp.put(textTryId,ty);
            }else if ( !ty.isAlive()){
                log.info("[ty.isAlive()]——>"+ ty.isAlive());
                ty = textTry.setup();
            }
            FutureTask<String> futureTask = new FutureTask<String>(new TextTryTask(ty,sayWord));
            textTryThreadConfig.getTrialThreadExecutor().execute(futureTask);
            String response = futureTask.get();
            return response;
        }catch (Exception e){
            throw e;
        }
    }
}