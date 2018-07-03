package com.ldh.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by ldh on 2018/7/2.
 */
@Component
public class TextTry {

    private static final Logger log = LoggerFactory.getLogger(TextTry.class);

    @Value(value = "${bin.path}")
    private   String SHELLPATH ;
    @Value(value = "${bin.name}")
    private   String BINNAME ;

    private Process pid;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;

    public TextTry setup() throws Exception{

        String[] cmd = { "/bin/sh", "-c",  this.SHELLPATH +  this.BINNAME};
        log.info("[cmd]——>" + "/bin/sh"+ " " + "-c"+ " " +  SHELLPATH + BINNAME);
        Process pid;
        try {
            pid = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        TextTry textTry = new TextTry();
        textTry.pid=pid;
        return textTry;
    }

    public boolean isAlive(){
        return pid.isAlive();
    }



    public Boolean isRead(){
        return pid.getInputStream()!=null;
    }

    public Boolean isWriter(){
        return pid.getOutputStream()!=null;
    }


    public BufferedReader getBufferedReader() {
        if (bufferedReader == null){
            try {
                 bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream(),"GBK"), 1024);
            }catch (Exception e){
                bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
                e.printStackTrace();
            }
        }
        return bufferedReader;
    }

    public BufferedWriter getBufferedWriter() {
        if ( bufferedWriter == null){
            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(pid.getOutputStream(),"GBK"),1024);
            }catch (Exception e) {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(pid.getOutputStream()),1024);
                e.printStackTrace();
            }
        }
        return bufferedWriter;
    }

    @Override
    public String toString() {
        return "TextTry{" +
                "SHELLPATH='" + this.SHELLPATH + '\'' +
                ", BINNAME='" +  this.BINNAME + '\'' +
                ", pid=" + pid +
                ", bufferedReader=" + bufferedReader +
                ", bufferedWriter=" + bufferedWriter +
                '}';
    }
}