package com.slyang.test.mybatis.usexml;

import com.slyang.test.mybatis.usexml.noxml.TestMapperNoXml;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) throws IOException {
		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

		SqlSession session = sqlSessionFactory.openSession();

		List<Map<String, Object>> resultMap;

		try {
			TestMapper testMapper = session.getMapper(TestMapper.class);
			resultMap = testMapper.test();

			TestMapperNoXml testMapperNoXml = session.getMapper(TestMapperNoXml.class);
			resultMap = testMapperNoXml.test();

			//
			// Configuration Executor newExecutor



			System.out.println("sdfasdfasdfasfsadfsdsad");
		} finally {
			session.close();
		}


	}

}
