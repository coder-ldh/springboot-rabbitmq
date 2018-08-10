package com.ldh.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.ldh.model.Message;
@Component
public class ProducerFactory implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{

	private static final Logger logger = LoggerFactory.getLogger(ProducerFactory.class);
	
	@Autowired
    private RabbitTemplate rabbitTemplate;
	
	public  void sendMessage(Message msg){
		/**
		 * 设置确认机制
		 */
		if(msg.getConfirm() != null && msg.getConfirm())
			rabbitTemplate.setConfirmCallback(this);
		
		/**
		 * 若routingKey无法找到一个合适的queue存储消息,broker则调用returnedMessage()
		 */
		if(msg.getMandatory() != null && msg.getMandatory())
			rabbitTemplate.setMandatory(true);
			rabbitTemplate.setReturnCallback(this);
		
		/**
		 * 设置开启事务
		 */
		if(msg.getTransacted() != null && msg.getTransacted())
			rabbitTemplate.setChannelTransacted(true);
		
		String jsonString = JSONObject.toJSONString(msg.getMsg());
		rabbitTemplate.convertAndSend(msg.getExchange(), msg.getRoutingKey(),jsonString.getBytes());
	}
	
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		logger.info("[correlationData——>]" + correlationData + "[ack——>]" + ack + "[cause——>]" + cause);  
        if (ack) {
            logger.info("消息发送成功");
        } else {
            logger.info("消息发送失败:" + cause);
        }  
	}

	@Override
	public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
			String exchange, String routingKey) {
		logger.info("[message——>]" + message.toString() + "[replyCode——>]" + replyCode + "[replyText——>]" + replyText + "[exchange——>]" + exchange + "[routingKey——>]" + routingKey);
	}
}