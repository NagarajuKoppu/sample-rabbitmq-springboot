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

import com.training.sample.rabbitmq.listener.RabbitMqFanoutQueue1Listener;
import com.training.sample.rabbitmq.listener.RabbitMqFanoutQueue2Listener;

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
	public SimpleMessageListenerContainer fanoutListenerContainer1(ConnectionFactory connectionFactory,
			MessageListenerAdapter fanoutAdapter1) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setMessageListener(fanoutAdapter1);
		container.setQueueNames(queueName1);
		return container;
	}

	@Bean
	public MessageListenerAdapter fanoutAdapter1(RabbitMqFanoutQueue1Listener rabbitMqFanoutQueue1Listener) {
		MessageListenerAdapter fanoutAdapter = new MessageListenerAdapter(rabbitMqFanoutQueue1Listener, "onMessage");
		fanoutAdapter.setMessageConverter(null);// this is to make sure no parsing on message to be done and supply
												// "Message" object directly to queue listener
		return fanoutAdapter;
	}

	@Bean
	public SimpleMessageListenerContainer fanoutListenerContainer2(ConnectionFactory connectionFactory,
			MessageListenerAdapter fanoutAdapter2) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setMessageListener(fanoutAdapter2);// parse message into Employee object
		container.setQueueNames(queueName2);
		return container;
	}

	@Bean
	public MessageListenerAdapter fanoutAdapter2(RabbitMqFanoutQueue2Listener rabbitMqFanoutQueue2Listener,
			MessageConverter jsonMessageConverter) {
		MessageListenerAdapter fanoutAdapter = new MessageListenerAdapter(rabbitMqFanoutQueue2Listener, "onMessage");
		fanoutAdapter.setMessageConverter(jsonMessageConverter);
		return fanoutAdapter;

	}
}
