package com.training.sample.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.training.sample.rabbitmq.listener.RabbitMqFanoutListener;

@Configuration
public class RabbitMqFanoutExchangeConfig {

	@Value("${app.mq.fanout.queuename1}")
	String queueName1;
	@Value("${app.mq.fanout.queuename2}")
	String queueName2;
	@Value("${app.mq.fanout.exchangename}")
	String exchangeName;

	@Bean
	public FanoutExchange fanoutExchange() {
		return new FanoutExchange(exchangeName, true, false);
	}

	@Bean
	public Queue fanoutQueue1() {
		return new Queue(queueName1, true);
	}

	@Bean
	public Queue fanoutQueue2() {
		return new Queue(queueName2, true);
	}

	@Bean
	public Binding fanoutBinding1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
	}

	@Bean
	public Binding fanoutBinding2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
		return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
	}

	@Bean
	public SimpleMessageListenerContainer fanoutListenerContainer(ConnectionFactory connectionFactory,
			MessageListenerAdapter fanoutAdapter) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setMessageListener(fanoutAdapter);
		container.setQueueNames(queueName1, queueName2);
		return container;
	}

	@Bean
	public MessageListenerAdapter fanoutAdapter(RabbitMqFanoutListener rabbitMqFanoutListener,
			MessageConverter jsonMessageConverter) {
		MessageListenerAdapter fanoutAdapter = new MessageListenerAdapter(rabbitMqFanoutListener, "onMessage");
		fanoutAdapter.setMessageConverter(jsonMessageConverter);
		return fanoutAdapter;

	}
}
