package com.ldh;

import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.ldh.conf.Constant;
import com.ldh.model.Message;
import com.ldh.model.User;
import com.ldh.producer.ProducerFactory;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class ApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationTests.class);
	
	public static final String ADD_ROUTING_KEY = "user.add.user";
	
	public static final AtomicInteger atomicInteger = new AtomicInteger(1);
	
	@Test
	public void contextLoads() {
	}
	
	@Autowired
	ProducerFactory producerFactory;
	
	@Test
	public void send(){
		User u = new User();
		u.setBirthday(new Date());
		u.setEmail("13477712166@qq.com");
		u.setNickName("python");
		Message message= new Message();
		message.setConfirm(true);
		message.setExchange(Constant.USER_EXCHANGE);
		message.setQueue(Constant.USER_QUEUE);
		message.setRoutingKey(ADD_ROUTING_KEY);
		message.setTransacted(false);
		message.setMandatory(true);
		message.setMsg(u);
		for (int i = 1; i <= 10; i++) {
			int andIncrement = atomicInteger.getAndIncrement();
			u.setPhone("1347771216" + andIncrement);
			u.setRealName("[第" + andIncrement + "条数据]");
			u.setBalance(new BigDecimal(andIncrement));
			producerFactory.sendMessage(message);
			logger.info("[andIncrement]——>[" + andIncrement + "]");
		}
		System.out.println(1);
	}
}