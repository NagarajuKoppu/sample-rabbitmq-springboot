package com.training.sample.rabbitmq.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RabbitMqControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("publish to topic exchange -- success")
	void test_topic_exchange() throws UnsupportedEncodingException, Exception {
		String response = mockMvc.perform(get("/publish/topic?empName=ananya&empId=12000")).andReturn().getResponse()
				.getContentAsString();
		assertEquals("Message published to the RabbitMQ exchange 'test-topic-exchange' Successfully", response,
				"response should match");
	}

	@Test
	@DisplayName("publish to direct exchange -- success")
	void test_direct_exchange() throws UnsupportedEncodingException, Exception {
		String response = mockMvc.perform(get("/publish/direct?empName=ananya&empId=12000")).andReturn().getResponse()
				.getContentAsString();
		assertEquals("Message published to the RabbitMQ exchange 'test-direct-exchange' Successfully", response,
				"response should match");
	}

	@Test
	@DisplayName("publish to fanout exchange -- success")
	void test_fanout_exchange() throws UnsupportedEncodingException, Exception {
		String response = mockMvc.perform(get("/publish/fanout?empName=ananya&empId=12000")).andReturn().getResponse()
				.getContentAsString();
		assertEquals("Message published to the RabbitMQ exchange 'test-fanout-exchange' Successfully", response,
				"response should match");
	}

	@Test
	@DisplayName("publish to header exchange -- success")
	void test_header_exchange() throws UnsupportedEncodingException, Exception {
		String response = mockMvc.perform(get("/publish/header?empName=ananya&empId=12000")).andReturn().getResponse()
				.getContentAsString();
		assertEquals("Message published to the RabbitMQ exchange 'test-header-exchange' Successfully", response,
				"response should match");
	}

}
