package com.slyang.test.dubbo.provider;

import com.slyang.test.dubbo.DemoService;


public class DemoMessageServiceImplV2 implements DemoService{

	public String sayHello(String name) {
		return "DemoMessageServiceImplV2  Hello  V2" + name;
	}
}
