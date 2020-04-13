package com.training.sample.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqDirectExchangeConfig {

	@Value("${app.mq.direct.queuename}")
	String queueName;
	@Value("${app.mq.direct.exchangename}")
	String exchangeName;
	@Value("${app.mq.direct.routingkey}")
	String routingKey;

	@Bean
	public DirectExchange directExchange() {
		return new DirectExchange(exchangeName, true, false);
	}

	@Bean
	public Queue directQueue() {
		return new Queue(queueName, true);
	}

	@Bean
	public Binding directBinding(Queue directQueue, DirectExchange directExchange) {
		return BindingBuilder.bind(directQueue).to(directExchange).with(routingKey);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
