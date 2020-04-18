package com.training.sample.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.training.sample.rabbitmq.dto.Employee;

@Component
public class RabbitMqTopicExchangeSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${app.mq.topic.exchangename}")
	String exchangeName;
	@Value("${app.mq.topic.messageroutingkey}")
	String messageRoutingKey;

	public String getExchangeName() {
		return exchangeName;
	}

	public void send(Employee employee) {
		rabbitTemplate.convertAndSend(exchangeName, messageRoutingKey, employee);
		System.out.println("Send msg to direct exchange: " + exchangeName + " with payload: " + employee);

	}
}
