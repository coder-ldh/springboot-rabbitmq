package com.ldh.service;

import com.ldh.constant.TextTryConstant;
import com.ldh.entity.TextTry;
import com.ldh.trialutil.TextTryThreadConfig;
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
            TextTry textTryByTextTryId = TextTryConstant.getTEXTTRYS().get(sayWord);
            if (textTryByTextTryId == null ){
                textTryByTextTryId = textTry.setup();
                TextTryConstant.getTEXTTRYS().put(textTryId,textTryByTextTryId);
            }if (textTryByTextTryId.isRead()){

            }if (textTryByTextTryId.isWriter()){

            }
            FutureTask<String> futureTask = new FutureTask<String>(new TextTryTask(textTryByTextTryId,sayWord));
            textTryThreadConfig.getTrialThreadExecutor().execute(futureTask);
            String response = futureTask.get();
            log.info("[response]——>" + response);
            return response;
        }catch (Exception e){
            throw e;
        }
    }
}