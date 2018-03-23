package com.slyang.test.dubbo.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {

	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
				new String[]{"spring-dubbo-provider.xml"});
		context.start();
		// press any key to exit
		System.in.read();
	}

}
