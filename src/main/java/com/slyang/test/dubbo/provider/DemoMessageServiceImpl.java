package com.slyang.test.dubbo.provider;

import com.slyang.test.dubbo.DemoService;


public class DemoMessageServiceImpl implements DemoService{

	public String sayHello(String name) {
		return "DemoMessageServiceImpl Hello " + name;
	}
}
