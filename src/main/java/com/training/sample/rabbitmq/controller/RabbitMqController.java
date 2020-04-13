package com.training.sample.rabbitmq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.sample.rabbitmq.dto.Employee;
import com.training.sample.rabbitmq.service.RabbitMqDirectExchangeSender;
import com.training.sample.rabbitmq.service.RabbitMqFanoutExchangeSender;
import com.training.sample.rabbitmq.service.RabbitMqHeaderExchangeSender;
import com.training.sample.rabbitmq.service.RabbitMqTopicExchangeSender;

@RestController
@RequestMapping(value = "/publish")
public class RabbitMqController {

	@Autowired
	RabbitMqDirectExchangeSender rabbitMqDirectExchangeSender;
	@Autowired
	RabbitMqFanoutExchangeSender rabbitMqFanoutExchangeSender;
	@Autowired
	RabbitMqTopicExchangeSender rabbitMqTopicExchangeSender;
	@Autowired
	RabbitMqHeaderExchangeSender rabbitMqHeaderExchangeSender;

	@GetMapping(value = "/direct")
	public String publishToDirectExchange(@RequestParam("empName") String empName,
			@RequestParam("empId") Integer empId) {

		rabbitMqDirectExchangeSender
				.send(Employee.builder().withId(empId).withName(empName).withSalary(1000.50).build());

		return "Message published to the RabbitMQ exchange '" + rabbitMqDirectExchangeSender.getExchangeName()
				+ "' Successfully";
	}

	@GetMapping(value = "/fanout")
	public String publishToFanoutExchange(@RequestParam("empName") String empName,
			@RequestParam("empId") Integer empId) {

		rabbitMqFanoutExchangeSender
				.send(Employee.builder().withId(empId).withName(empName).withSalary(1000.50).build());

		return "Message published to the RabbitMQ exchange '" + rabbitMqFanoutExchangeSender.getExchangeName()
				+ "' Successfully";
	}

	@GetMapping(value = "/topic")
	public String publishToTopicExchange(@RequestParam("empName") String empName,
			@RequestParam("empId") Integer empId) {

		rabbitMqTopicExchangeSender
				.send(Employee.builder().withId(empId).withName(empName).withSalary(1000.50).build());

		return "Message published to the RabbitMQ exchange '" + rabbitMqTopicExchangeSender.getExchangeName()
				+ "' Successfully";
	}

	@GetMapping(value = "/header")
	public String publishToHeaderExchange(@RequestParam("empName") String empName,
			@RequestParam("empId") Integer empId) {

		rabbitMqHeaderExchangeSender
				.send(Employee.builder().withId(empId).withName(empName).withSalary(1000.50).build());

		return "Message published to the RabbitMQ exchange '" + rabbitMqHeaderExchangeSender.getExchangeName()
				+ "' Successfully";
	}

}