package com.ldh.conf;

public final class Constant {

	public static final String RABBITMQ_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
	public static final String RABBITMQ_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
	
	
	/** 用户模块**/
	public static final String USER_QUEUE = "user.queue";
	public static final String USER_EXCHANGE = "user.exchange";
    public static final String USER_ADD_ROUTING_KEY = "user.add.*";
    public static final String USER_UPDATE_ROUTING_KEY = "user.update.*";
    public static final String USER_DELETE_ROUTING_KEY = "user.delete.*";
}