package com.training.sample.rabbitmq.listener;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.training.sample.rabbitmq.dto.Employee;

/**
 * @author Nagaraju_Koppu direct declarion of rabbit listener TODO: need to work
 *         on acknowledgement options
 */
@Component
public class RabbitMqDirectListener {

	@RabbitListener(queues = "${app.mq.direct.queuename}")
	public void onMessage(Employee emp, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag)
			throws IOException {
		System.out.println("--------------Received message-----------");
		System.out.println(emp);
		channel.basicAck(deliveryTag, false);
	}

	// using spring cloud stream
//	 @StreamListener(Sink.INPUT)
//	    public void in(String in, @Header(AmqpHeaders.CHANNEL) Channel channel,
//	            @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws Exception {
//	        System.out.println(in);
//	        Thread.sleep(60_000);
//	        channel.basicAck(tag, false);
//	        System.out.println("Ackd");
//	    }
//	Also set the below property in configuration: <channel> --> input
//	spring.cloud.stream.rabbit.bindings.<channel>.consumer.acknowledge-mode=MANUAL
}
