package com.slyang.test.dubbo.provider;

import com.slyang.test.dubbo.DemoService;


public class DemoServiceImplV2 implements DemoService{

	public String sayHello(String name) {
		return "Hello  V2" + name;
	}
}
