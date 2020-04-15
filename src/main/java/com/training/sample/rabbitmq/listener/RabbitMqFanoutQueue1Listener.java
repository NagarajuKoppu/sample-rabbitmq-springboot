package com.training.sample.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Nagaraju_Koppu TODO: need to work on acknowledgement options
 */
@Component
public class RabbitMqFanoutQueue1Listener {

	@Autowired
	MessageConverter jsonMessageConverter;

	public void onMessage(Message msg) {
		System.out.println("--------------Received message-----------");
		System.out.println("Queue name: " + msg.getMessageProperties().getConsumerQueue());
		System.out.println("Message:  " + jsonMessageConverter.fromMessage(msg));
	}
}
