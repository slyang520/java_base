package com.slyang.test.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

public class TestActiviti {

	/**使用代码创建工作流需要的23张表*/
	@Test
	public void createTable(){
		//流程引擎ProcessEngine对象，所有操作都离不开引擎对象
		ProcessEngineConfiguration processEngineConfiguration =
				ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();

		//连接数据库的配置
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/test_activiti?useUnicode=true&characterEncoding=utf8");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("123456");

		//三个配置
		//1.先删除表，再创建表：
		//processEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP="create-drop";
		//2.不能自动创建表，需要表存在：
		 //processEngineConfiguration.DB_SCHEMA_UPDATE_FALSE="false"
		//3.如果表存在，就自动创建表：
		 //processEngineConfiguration.DB_SCHEMA_UPDATE_TRUE="true"
		processEngineConfiguration.setDatabaseSchema(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		//获取工作流的核心对象，ProcessEngine对象
		ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();
		System.out.println("processEngine:"+processEngine+"Create Success!!");
	}

}
