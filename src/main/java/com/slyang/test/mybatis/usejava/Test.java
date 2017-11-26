package com.slyang.test.mybatis.usejava;

import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


public class Test {

	public static void main(String[] args) {

		List<Map<String, Object>> resultMap;

		DataSource dataSource = MyDataSourceFactory.GetdataSource_1();
		TransactionFactory transactionFactory = new JdbcTransactionFactory();
		Environment environment = new Environment(
				"development",
				transactionFactory,
				dataSource);
		Configuration configuration = new Configuration(environment);
		configuration.addMapper(TestMapper.class);

		SqlSessionFactory sqlSessionFactory =
				new SqlSessionFactoryBuilder().build(configuration);

		SqlSession session = sqlSessionFactory.openSession();

		try {
			TestMapper testMapper = session.getMapper(TestMapper.class);
			resultMap = testMapper.test();
		} finally {
			session.close();
		}


		MapperRegistry mapperRegistry;

	}


}
