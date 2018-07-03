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

        //String[] cmd = { "cmd"};
        String[] cmd = { "/bin/sh", "-c",  this.SHELLPATH +  this.BINNAME};
        log.info("[cmd]——>" + "/bin/sh"+ " " + "-c"+ " " +  SHELLPATH + BINNAME);
        Process pid;
        BufferedReader  bufferedReader =null;
        BufferedWriter bufferedWriter=null;
        try {
            pid = Runtime.getRuntime().exec(cmd);
            try {
                  bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream(),"GBK"), 1024);
            }catch (Exception e){
                 bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024);
                e.printStackTrace();
            }

            try {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(pid.getOutputStream(),"GBK"),1024);
            }catch (Exception e) {
                bufferedWriter = new BufferedWriter(new OutputStreamWriter(pid.getOutputStream()),1024);
                e.printStackTrace();
            }
            this.bufferedReader = bufferedReader;
            this.bufferedWriter = bufferedWriter;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        TextTry textTry = new TextTry();
        textTry.pid=pid;
        textTry.bufferedReader= bufferedReader;
        textTry.bufferedWriter = bufferedWriter;
        return textTry;
    }

    public boolean isAlive(){
        return pid.isAlive() || pid.getInputStream()!=null || pid.getOutputStream()!=null;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public BufferedWriter getBufferedWriter() {
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