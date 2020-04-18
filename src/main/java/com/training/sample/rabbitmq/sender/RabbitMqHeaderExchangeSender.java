package com.training.sample.rabbitmq.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.training.sample.rabbitmq.dto.Employee;

@Component
public class RabbitMqHeaderExchangeSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;
	@Autowired
	private MessageConverter jsonMessageConverter;

	@Value("${app.mq.header.exchangename}")
	String exchangeName;
	@Value("${app.mq.header.headername}")
	String header;
	@Value("${app.mq.header.messageroutingkey}")
	String messageRoutingKey;

	public String getExchangeName() {
		return exchangeName;
	}

	public void send(Employee employee) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader(header, messageRoutingKey);
		Message message = jsonMessageConverter.toMessage(employee, messageProperties);
		rabbitTemplate.send(exchangeName, "", message);
		System.out.println("Send msg to direct exchange: " + exchangeName + " with payload: " + employee);

	}
}
