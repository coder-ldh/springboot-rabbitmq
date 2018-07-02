package com.ldh.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by ldh on 2018/7/2.
 */
@Component
public class TextTry {

    @Value(value = "${bin.path}")
    private   String SHELLPATH ;
    @Value(value = "${bin.name}")
    private   String BINNAME ;

    private Process pid;
    private BufferedReader bufferedReader = null;
    private BufferedWriter bufferedWriter = null;

    public TextTry setup(){

        String[] cmd = { "cmd"};
        Process pid;
        try {
            pid = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(pid.getInputStream()), 1024*1024);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(pid.getOutputStream()),1024*1024);

        TextTry textTry = new TextTry();
        textTry.pid=pid;
        textTry.bufferedReader=bufferedReader;
        textTry.bufferedWriter=bufferedWriter;
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
}