package com.slyang.test.activiti;

import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-activiti.xml")
public class TestActivitiSpring2 {

	Logger logger = LoggerFactory.getLogger(TestActivitiSpring2.class);

	@Autowired
	ProcessEngine processEngine;

	/**
	 * 流程仓库Service，用于管理流程仓库，
	 * 例如
	 * 部署、
	 * 删除、
	 * 读取
	 * 流程资源
	 * <p>
	 * (
	 * 部署
	 * ACT_RE_DEPLOYMENT
	 * ACT_RE_PROCDEF
	 * 部署表流程的资源文件
	 * ACT_GE_BYTEARRAY
	 * <p>
	 * )
	 */
	@Autowired
	RepositoryService repositoryService;

	/**
	 * 表单Service，
	 * 用于读取流程、
	 * 任务相关的表单数据
	 * <p>
	 * ACT_HI_ACTINST
	 * ACT_HI_DETAIL
	 * ...
	 */
	@Autowired
	FormService formService;

	@Autowired
	RuntimeService runtimeService;

	/**
	 * 任务Service，用于
	 * 管理和查询任务，
	 * 例如签收、办理、指派等
	 *
	 * 签收
	 * taskService.claim(String taskId, String userId);
	 * 办理
	 * taskService.complete(String taskId, Map<String, Object> variables);
	 * 指派
	 * taskService.setAssignee(taskId, userId);
	 */
	@Autowired
	TaskService taskService;

	@Autowired
	IdentityService identityService;

	@Autowired
	HistoryService historyService;

	/**
	 */
	@Test
	public void helloTest() {
		assertNotNull(repositoryService);
		assertNotNull(runtimeService);
		assertNotNull(processEngine);
	}


	/**
	 * 部署一个工作流  流程
	 * （重复部署会用多个流程实例 TODO fix）
	 */
	@Test
	public void DeploymentActiviti() {
		Deployment deployment =
				repositoryService
						.createDeployment()
						.addClasspathResource("activiti/leave.bpmn")
						.name("流程测试")
						.tenantId("12345")
						.category("")
						.deploy();

//		BpmnModel bpmnModel;
//		Collection<FlowElement> flowElements = model.getMainProcess().getFlowElements();



	}

	/**
	 * 启动一个流程
	 */
	@Test
	public void StartActiviti() {
		/**
		 * 找出一个流程
		 *
		 * 根据KEY找流程
		 * 对于流程文件里面的ID
		 */
		List<ProcessDefinition> processDefinitionList =
				repositoryService.createProcessDefinitionQuery()
						//根据流程ID查
						//.processDefinitionId
						.processDefinitionKey("leave").list();
		assertNotNull(processDefinitionList);

		/**
		 * 启动一个流程
		 */
 		String startUserId = "startUserId";
		String managerUserID = "manager_userId";
		String hrUserID = "hr_userId";


		ProcessDefinition processDefinition = processDefinitionList.get(processDefinitionList.size()-1);


		// 设置变量
		Map<String, Object> vars = new HashMap<String, Object>();

		vars.put("applyUserId", startUserId);
		vars.put("deptLeaderAudit", managerUserID);
		vars.put("hrAudit", hrUserID);

		// 设置谁启动了流程
		identityService.setAuthenticatedUserId(startUserId);
		ProcessInstance processInstance = runtimeService.
				startProcessInstanceById(processDefinition.getId(), vars);
		identityService.setAuthenticatedUserId(null);


		/**
		 * 模拟部门经理同意
		 */
		//1 查找代办事项
		//1.1 等待签收的任务
		List<Task> toClaimList = taskService
				.createTaskQuery()
				.taskCandidateUser(managerUserID).active().list();


		taskService.setAssignee(toClaimList.get(0).getId(),managerUserID);

		//1.2 已经签收的任务
		List<Task> todoList =
				taskService
						.createTaskQuery()
						.taskAssignee(managerUserID).active().list();
		/**
		 * 1.签收
		 */
		taskService.claim(toClaimList.get(0).getId(), managerUserID);

		/**
		 * 2.办理
		 * 同意
		 */
		Map<String, Object> managerVariables = new HashMap<>();
		managerVariables.put("deptLeaderPass", true);
		taskService.complete(toClaimList.get(0).getId(), managerVariables);

		/**
		 *
		 * 模拟HR同意流程
		 */
		//1 查找代办事项
		//1.1 等待签收的任务
		List<Task> toClaimListHR = taskService
				.createTaskQuery()
				.taskCandidateUser(hrUserID).active().list();
		//1.2 已经签收的任务
		List<Task> todoListHR =
				taskService
						.createTaskQuery()
						.taskAssignee(hrUserID).active().list();

		taskService.claim(toClaimListHR.get(0).getId(), hrUserID);
		/**
		 * 2.办理
		 * 同意
		 */
		Map<String, Object> hrVariables = new HashMap<>();
		hrVariables.put("hrPass", true);
		taskService.complete(toClaimListHR.get(0).getId(), hrVariables);

		/**
		 * 销假
		 * assignee (直接进入签收环节)
		 */
		//3 已经签收的任务
		List<Task> todoListStart =
				taskService
						.createTaskQuery()
						.taskAssignee(startUserId).active().list();
//
		taskService.complete(todoListStart.get(0).getId(), null);


	}


	/**
	 * 查询历史节点运行情况
	 */
	@Test
	public void queryHistoryAct(){

		List<HistoricActivityInstance> list=processEngine.getHistoryService() // 历史任务Service
				.createHistoricActivityInstanceQuery() // 创建历史活动实例查询
				.taskAssignee("startUserId")
				.finished() // 查询已经完成的任务
				.list();
		
		for(HistoricActivityInstance hai:list){
			logger.error("任务ID:"+hai.getId());
			logger.error("流程实例ID:"+hai.getProcessInstanceId());
			logger.error("活动名称："+hai.getActivityName());
			logger.error("办理人："+hai.getAssignee());
			logger.error("开始时间："+hai.getStartTime());
			logger.error("结束时间："+hai.getEndTime());
			logger.error("===========================");
		}

	}


}