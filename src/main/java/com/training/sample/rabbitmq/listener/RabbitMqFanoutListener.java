package com.training.sample.rabbitmq.listener;

import org.springframework.stereotype.Component;

import com.training.sample.rabbitmq.dto.Employee;

/**
 * @author Nagaraju_Koppu TODO: need to work on acknowledgement options
 */
@Component
public class RabbitMqFanoutListener {

	public void onMessage(Employee emp) {
		System.out.println("--------------Received message-----------");
		System.out.println(emp);
	}
}
