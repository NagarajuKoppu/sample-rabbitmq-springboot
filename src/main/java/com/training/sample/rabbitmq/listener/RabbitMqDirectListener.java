package com.training.sample.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.training.sample.rabbitmq.dto.Employee;

/**
 * @author Nagaraju_Koppu direct declarion of rabbit listener TODO: need to work
 *         on acknowledgement options
 */
@Component
public class RabbitMqDirectListener {

	@RabbitListener(queues = "${app.mq.direct.queuename}")
	public void onMessage(Employee emp) {
		System.out.println("--------------Received message-----------");
		System.out.println(emp);
	}
}
