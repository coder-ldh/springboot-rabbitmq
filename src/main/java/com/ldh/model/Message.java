package com.ldh.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Message implements Serializable{

	/**
	 */
	private static final long serialVersionUID = 7045666728699679283L;
	/**
	 * 路由键
	 */
	private String routingKey;
	/**
	 * 交换机
	 */
	private String exchange;
	/**
	 * 队列
	 */
	private String queue;
	/**
	 * mandatory的作用[执行returnedMessage()方法]
	 * 当mandatory标志位设置为true时，如果exchange根据自身类型和消息routingKey无法找到一个合适的queue存储消息，
	 * 那么broker会调用basic.return方法将消息返还给生产者;当mandatory设置为false时，出现上述情况broker会直接将消息丢弃;
	 * 通俗的讲，mandatory标志告诉broker代理服务器至少将消息route到一个队列中，否则就将消息return给发送者;
	 */
	private Boolean mandatory;
	/**
	 * 设置确认机制
	 */
	private Boolean confirm;
	/**
	 * 设置事务
	 */
	private Boolean transacted;
	/**
	 * 消息
	 */
	private Object msg;
}