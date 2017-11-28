package com.slyang.test.mybatis.usespring;


import com.slyang.test.mybatis.usespring.mapper.TestMapper;
import com.slyang.test.mybatis.usespring.mapper.TestXMLMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-mybatis.xml")
public class SpringMybatis {

	@Resource
	TestMapper testMapper;

	@Resource
	TestXMLMapper testXMLMapper;

	@Test
	public void test1() {
		testMapper.test();
		testMapper.test2();
		testXMLMapper.test();
		testXMLMapper.test2();
	}

}
