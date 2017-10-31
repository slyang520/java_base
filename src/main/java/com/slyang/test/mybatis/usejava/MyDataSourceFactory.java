package com.slyang.test.mybatis.usejava;


import org.apache.ibatis.datasource.pooled.PooledDataSource;

import javax.sql.DataSource;

public class MyDataSourceFactory {

	public static DataSource GetdataSource_1() {

		//  String driver, String url, String username, String password
		DataSource dataSource = new PooledDataSource(
				"com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306/XGo-bbs?characterEncoding=utf8&useSSL=true",
				"root",
				"123456"
		);

		return dataSource;
	}

}
