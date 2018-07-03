package com.ldh.result;

/**
 * Created by ldh on 2018/7/3.
 */
public class RobotResult<T> {

    private String RobotId;

    private String message;

    private T object;

    public RobotResult(String robotId, String message, T object) {
        RobotId = robotId;
        this.message = message;
        this.object = object;
    }

    public RobotResult() {
    }

    public static RobotResult success(){
        RobotResult robotResult = new RobotResult();

        return robotResult;
    }
}