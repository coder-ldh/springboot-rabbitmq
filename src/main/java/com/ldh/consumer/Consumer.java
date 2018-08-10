package com.ldh.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.ldh.conf.Constant;
import com.ldh.mapper.UserMapper;
import com.ldh.model.User;
import com.rabbitmq.client.Channel;


/**
 * 消费者
 */
@Component
public class Consumer{

	private static final Logger logger = LoggerFactory.getLogger(Consumer.class);
	
	@Autowired
	private UserMapper userMapper;
	
    /**
     * 监听新增
     */
	@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
	@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = Constant.USER_QUEUE, durable = "true"),
            exchange = @Exchange(
            		value = Constant.USER_EXCHANGE, 
            		durable = "true", 
            		type = ExchangeTypes.TOPIC
            		//,arguments=@Argument(name = Constant.RABBITMQ_DEAD_LETTER_EXCHANGE,value = "")
            		),
            key = Constant.USER_ADD_ROUTING_KEY
            	//,arguments=@Argument(name = Constant.RABBITMQ_DEAD_LETTER_ROUTING_KEY,value = "")
            ))
    public void processAdd(Message message,Channel channel) throws Exception {
		try {
			byte[] body = message.getBody();
			String s = new String(body);
			User msg = JSONObject.parseObject(s, User.class);
			MessageProperties messageProperties = message.getMessageProperties();
			logger.info("[User][ADD]——>" + "[msg]" + msg.toString() + "[messageProperties]" + messageProperties);
			
			//业务代码实现幂等
			User user = userMapper.selectByPhone(msg.getPhone());
			if (user == null) {
				int result = userMapper.insert(msg);
				if (result > 0) {
					channel.basicAck(messageProperties.getDeliveryTag(), false);
				}
			}
			int intValue = msg.getBalance().intValue();
/*			if(intValue == 5){
				int a = 5/0 ;
				System.out.println(a);
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			/**
			 * deliveryTag:该消息的index
			 * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
			 * requeue：被拒绝的是否重新入队列
			 */
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
			throw new RuntimeException(e);
		}
    }
	
	
   /* *//**
     * 监听修改
     *//*
	@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = Constant.USER_QUEUE, durable = "true"),
            exchange = @Exchange(value = Constant.USER_EXCHANGE, durable = "true", type = ExchangeTypes.TOPIC),
            key = Constant.USER_UPDATE_ROUTING_KEY))
    public void processUpdate(Message message,Channel channel) throws Exception {
		try {
			byte[] body = message.getBody();
			String s = new String(body);
			User msg = JSONObject.parseObject(s, User.class);
			MessageProperties messageProperties = message.getMessageProperties();
			logger.info("[User][UPDATE]——>" + "[msg]" + msg.toString() + "[messageProperties]" + messageProperties);
			int result = userMapper.updateByKey(msg);
			if (result > 0) {
				channel.basicAck(messageProperties.getDeliveryTag(), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}
    }
	
	*//**
     * 监听删除
     *//*
	@RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = Constant.USER_QUEUE, durable = "true"),
            exchange = @Exchange(value = Constant.USER_EXCHANGE, durable = "true", type = ExchangeTypes.TOPIC),
            key = Constant.USER_DELETE_ROUTING_KEY))
    public void processDelete(Message message,Channel channel) throws Exception {
		try {
			byte[] body = message.getBody();
			String s = new String(body);
			User msg = JSONObject.parseObject(s, User.class);
			MessageProperties messageProperties = message.getMessageProperties();
			logger.info("[User][DELETE]——>" + "[msg]" + msg.toString() + "[messageProperties]" + messageProperties);
			int result = userMapper.deleteByKey(msg.getUserId());
			if (result > 0) {
				channel.basicAck(messageProperties.getDeliveryTag(), false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
		}
    }*/
}