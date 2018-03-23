package com.slyang.test.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-activiti.xml")
public class TestActivitiSpring {

	@Autowired
	ProcessEngineConfiguration processEngineConfiguration;

	/**
	 *
	 */
	@Test
	public void createTable() {
		//获取工作流的核心对象，ProcessEngine对象
		ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();
		System.out.println("processEngine:"+processEngine+"Create Success!!");
	}

	/**
	 * 部署流程定义
	 */
	@Test
	public void createDeployment() {
		//获取工作流的核心对象，ProcessEngine对象
		ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();

		//与流程定义和部署对象相关的Service
		RepositoryService repositoryService=processEngine.getRepositoryService();

		DeploymentBuilder deploymentBuilder=repositoryService.createDeployment();//创建一个部署对象
		//添加部署的名称
		deploymentBuilder.name("helloWorld入门程序");
		//从classpath的资源加载，一次只能加载一个文件
		deploymentBuilder.addClasspathResource("conf/myworkflow.bpmn");

		Deployment deployment=deploymentBuilder.deploy();//完成部署

		//打印我们的流程信息
		System.out.println("流程Id:"+deployment.getId());
		System.out.println("流程Name:"+deployment.getName());
	}

	/**
	 * 启动流程引擎*
	 */
	@Test
	public void startProcessInstance(){
		//获取工作流的核心对象，ProcessEngine对象
		ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();
		//获取流程启动Service
		RuntimeService runtimeService=processEngine.getRuntimeService();
		//使用流程定义的key，key对应bpmn文件对应的id，
		//(也是act_re_procdef表中对应的KEY_字段),默认是按照最新版本启动
		String processDefinitionkey="myProcess_1";//
		//获取流程实例对象
		ProcessInstance processInstance=runtimeService.startProcessInstanceByKey(processDefinitionkey);
		System.out.println("流程实例ID："+processInstance.getId());//流程实例ID
		System.out.println("流程定义ID："+processInstance.getProcessDefinitionId());//流程定义ID
	}

}
