package com.ldh.producer;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ldh.conf.Constant;
import com.ldh.model.Message;
import com.ldh.model.User;
@Component
public class Producer {
	
	public static final String USER_ADD_ROUTING_KEY = "user.user";
	
	@Autowired
	ProducerFactory producerFactory;
	
	public void send(){
		User u = new User();
		u.setBalance(BigDecimal.ONE);
		u.setBirthday(new Date());
		u.setEmail("917764107@");
		u.setNickName("haha");
		u.setRealName("adasdasda");
		Message message= new Message();
		message.setConfirm(true);
		message.setExchange(Constant.USER_EXCHANGE);
		message.setQueue(Constant.USER_QUEUE);
		message.setRoutingKey(USER_ADD_ROUTING_KEY);
		message.setTransacted(false);
		message.setMsg(u);
		producerFactory.sendMessage(message);
	}
}
