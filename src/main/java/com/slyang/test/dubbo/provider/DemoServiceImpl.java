package com.slyang.test.dubbo.provider;

import com.slyang.test.dubbo.DemoService;


public class DemoServiceImpl implements DemoService{

	public String sayHello(String name) {
		return "Hello " + name;
	}
}
