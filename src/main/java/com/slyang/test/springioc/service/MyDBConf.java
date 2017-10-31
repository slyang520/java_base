package com.slyang.test.springioc.service;

import org.springframework.beans.factory.annotation.Value;

public class MyDBConf {

	/**
	 * 占位符方式
	 */
	@Value("${jdbc.url}")
	private String JDBCUrl;
	@Value("${jdbc.driver}")
	private String JDBCDriver;
	@Value("${jdbc.username")
	private String JDBCUserName;
	@Value("${jdbc.password}")
	private String JDBCPassword;

	@Value("${jdbc2.url}")
	private String JDBCUrl2;
	@Value("${jdbc2.driver}")
	private String JDBCDriver2;
	@Value("${jdbc2.username")
	private String JDBCUserName2;
	@Value("${jdbc2.password}")
	private String JDBCPassword2;

	@Override
	public String toString() {
		return "MyDBConf{" +
				"JDBCUrl='" + JDBCUrl + '\'' +
				", JDBCDriver='" + JDBCDriver + '\'' +
				", JDBCUserName='" + JDBCUserName + '\'' +
				", JDBCPassword='" + JDBCPassword + '\'' +
				", JDBCUrl2='" + JDBCUrl2 + '\'' +
				", JDBCDriver2='" + JDBCDriver2 + '\'' +
				", JDBCUserName2='" + JDBCUserName2 + '\'' +
				", JDBCPassword2='" + JDBCPassword2 + '\'' +
				'}';
	}
}
