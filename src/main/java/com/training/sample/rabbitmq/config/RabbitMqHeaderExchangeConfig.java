package com.training.sample.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqHeaderExchangeConfig {

	@Value("${app.mq.header.queuename1}")
	String queueName1;
	@Value("${app.mq.header.queuename2}")
	String queueName2;
	@Value("${app.mq.header.exchangename}")
	String exchangeName;
	@Value("${app.mq.header.headername}")
	String header;
	@Value("${app.mq.header.headervalue1}")
	String headerValue1;
	@Value("${app.mq.header.headervalue2}")
	String headerValue2;

	@Bean
	public HeadersExchange headersExchange() {
		return new HeadersExchange(exchangeName, true, false);
	}

	@Bean
	public Queue headerQueue1() {
		return new Queue(queueName1, true);
	}

	@Bean
	public Queue headerQueue2() {
		return new Queue(queueName2, true);
	}

	@Bean
	public Binding headerBinding1(Queue headerQueue1, HeadersExchange headersExchange) {
		return BindingBuilder.bind(headerQueue1).to(headersExchange).where(header).matches(headerValue1);
	}

	@Bean
	public Binding headerBinding2(Queue headerQueue2, HeadersExchange headersExchange) {
		return BindingBuilder.bind(headerQueue2).to(headersExchange).where(header).matches(headerValue2);
	}

}
