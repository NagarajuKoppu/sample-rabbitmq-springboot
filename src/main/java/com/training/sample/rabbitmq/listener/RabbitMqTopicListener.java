package com.training.sample.rabbitmq.listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.training.sample.rabbitmq.dto.Employee;

/**
 * @author Nagaraju_Koppu direct declaration of rabbit listener
 */
@Component
public class RabbitMqTopicListener implements ChannelAwareMessageListener {

	@Autowired
	MessageConverter jsonMessageConverter;

	@Override
	public void onMessage(Message message, Channel channel) throws Exception {
		onMessage((Employee) jsonMessageConverter.fromMessage(message), channel,
				message.getMessageProperties().getDeliveryTag());

	}

	public void onMessage(Employee emp, Channel channel, long deliveryTag) throws Exception {
		System.out.println("--------------Received message-----------");
		System.out.println(emp);
		channel.basicAck(deliveryTag, true);
	}

}
