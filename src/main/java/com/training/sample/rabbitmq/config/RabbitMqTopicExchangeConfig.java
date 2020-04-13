package com.training.sample.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqTopicExchangeConfig {

	@Value("${app.mq.topic.queuename1}")
	String queueName1;
	@Value("${app.mq.topic.queuename2}")
	String queueName2;
	@Value("${app.mq.topic.queuename3}")
	String queueName3;
	@Value("${app.mq.topic.exchangename}")
	String exchangeName;
	@Value("${app.mq.topic.routingkey1}")
	String routingKey1;
	@Value("${app.mq.topic.routingkey2}")
	String routingKey2;
	@Value("${app.mq.topic.routingkey3}")
	String routingKey3;

	@Bean
	public TopicExchange topicExchange() {
		return new TopicExchange(exchangeName, true, false);
	}

	@Bean
	public Queue topicQueue1() {
		return new Queue(queueName1, true);
	}

	@Bean
	public Queue topicQueue2() {
		return new Queue(queueName2, true);
	}

	@Bean
	public Queue topicQueue3() {
		return new Queue(queueName3, true);
	}

	@Bean
	public Binding topicBinding1(Queue topicQueue1, TopicExchange topicExchange) {
		return BindingBuilder.bind(topicQueue1).to(topicExchange).with(routingKey1);
	}

	@Bean
	public Binding topicBinding2(Queue topicQueue2, TopicExchange topicExchange) {
		return BindingBuilder.bind(topicQueue2).to(topicExchange).with(routingKey2);
	}

	@Bean
	public Binding topicBinding3(Queue topicQueue3, TopicExchange topicExchange) {
		return BindingBuilder.bind(topicQueue3).to(topicExchange).with(routingKey3);
	}

}
