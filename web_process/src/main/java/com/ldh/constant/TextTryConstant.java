package com.ldh.constant;

import com.ldh.entity.TextTry;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by ldh on 2018/7/2.
 */
public class TextTryConstant {

    private static ConcurrentHashMap<String, TextTry> TEXTTRYS ;

    public static ConcurrentHashMap<String, TextTry> getTEXTTRYS(){
        if(TEXTTRYS == null){
            TEXTTRYS = new ConcurrentHashMap<>();
        }
        return TEXTTRYS;
    }
}