package com.training.sample.rabbitmq.listener;

import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.training.sample.rabbitmq.dto.Employee;

/**
 * @author Nagaraju_Koppu TODO: need to work on acknowledgement options
 */
@Component
public class RabbitMqFanoutQueue2Listener {

	@Autowired
	MessageConverter jsonMessageConverter;

	public void onMessage(Employee emp) {
		System.out.println("--------------Received message-----------");
		System.out.println("Queue name: queue-2");
		System.out.println(emp);
	}
}
