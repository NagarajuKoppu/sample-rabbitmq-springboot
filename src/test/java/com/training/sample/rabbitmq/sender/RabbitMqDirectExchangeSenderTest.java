package com.training.sample.rabbitmq.sender;

//@ContextConfiguration(loader = RabbitMqDirectExchangeSenderTest.NoBeansOverrideAnnotationConfigContextLoader.class)
//@ExtendWith(SpringExtension.class)
//@RabbitAvailable
//@SpringBootTest
public class RabbitMqDirectExchangeSenderTest {
	/*
	 * 
	 * @Autowired private Queue directQueue;
	 * 
	 * @Autowired private RabbitTemplate rabbitTemplate;
	 * 
	 * @Autowired private RabbitListenerTestHarness harness;
	 * 
	 * @Test public void testTwoWay() throws Exception {
	 * assertEquals(rabbitTemplate.convertSendAndReceive(directQueue.getName(),
	 * "foo"), "FOO");
	 * 
	 * InvocationData invocationData = this.harness.getNextInvocationDataFor("foo",
	 * 10, TimeUnit.SECONDS); assertThat(invocationData).isNotNull();
	 * assertThat((String) invocationData.getArguments()[0]).isEqualTo("foo");
	 * assertThat((String) invocationData.getResult()).isEqualTo("FOO"); }
	 * 
	 * public static class NoBeansOverrideAnnotationConfigContextLoader extends
	 * AnnotationConfigContextLoader {
	 * 
	 * @Override protected void customizeBeanFactory(DefaultListableBeanFactory
	 * beanFactory) { beanFactory.setAllowBeanDefinitionOverriding(false); }
	 * 
	 * }
	 * 
	 */}
