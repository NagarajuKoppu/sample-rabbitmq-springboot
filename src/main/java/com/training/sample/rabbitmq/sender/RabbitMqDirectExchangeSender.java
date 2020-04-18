package com.training.sample.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.training.sample.rabbitmq.dto.Employee;

@Component
public class RabbitMqDirectExchangeSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${app.mq.direct.exchangename}")
	String exchangeName;
	@Value("${app.mq.direct.routingkey}")
	String routingKey;

	public String getExchangeName() {
		return exchangeName;
	}

	public void send(Employee employee) {
		rabbitTemplate.convertAndSend(exchangeName, routingKey, employee);
		System.out.println("Send msg to direct exchange: " + exchangeName + " with payload: " + employee);

	}
}
