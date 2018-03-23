package com.slyang.test.dubbo.consumer;


import com.slyang.test.dubbo.DemoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-dubbo-consumer.xml")
public class ConsumerAnnotation {

	@Autowired
	@Qualifier(value = "demoService")
	DemoService demoService;

	@Autowired
	@Qualifier(value = "demoServiceV2")
	DemoService demoService2;

	@Autowired
	@Qualifier(value = "demoMessageService")
	DemoService demoService3;

	@Autowired
	@Qualifier(value = "demoMessageServiceV2")
	DemoService demoService4;

	@Test
	public void test1() {
		// execute remote invocation
		String hello1 = demoService.sayHello("world");
		System.out.println(hello1);
		String hello2 = demoService2.sayHello("world");
		System.out.println(hello2);

		String hello3 = demoService3.sayHello("world");
		System.out.println(hello3);
		String hello4 = demoService4.sayHello("world");
		System.out.println(hello4);

	}


}
