package com.slyang.test.activiti;


import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.javax.el.ExpressionFactory;
import org.activiti.engine.impl.javax.el.ValueExpression;
import org.activiti.engine.impl.juel.ExpressionFactoryImpl;
import org.activiti.engine.impl.juel.SimpleContext;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-activiti.xml")
public class ActivitiModelTest {

	@Autowired
	ProcessEngine processEngine;

	@Autowired
	RepositoryService repositoryService;

	@Test
	public void testCreate() throws UnsupportedEncodingException {

		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode editorNode = objectMapper.createObjectNode();
		editorNode.put("id", "canvas");
		editorNode.put("resourceId", "canvas");
		ObjectNode stencilSetNode = objectMapper.createObjectNode();
		stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
		editorNode.put("stencilset", stencilSetNode);
		Model modelData = repositoryService.newModel();

		ObjectNode modelObjectNode = objectMapper.createObjectNode();
		modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, "hello1111");
		modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
		String description = "hello1111";
		modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
		modelData.setMetaInfo(modelObjectNode.toString());
		modelData.setName("hello1111");
		modelData.setKey("12313123");

		//保存模型
		repositoryService.saveModel(modelData);
		repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

	}

	@Test
	public void testDeloy() throws IOException {

		Model modelData = repositoryService.getModel("7501");

		com.fasterxml.jackson.databind.node.ObjectNode modelNode =
				(com.fasterxml.jackson.databind.node.ObjectNode) new com.fasterxml.jackson.databind.ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));

		byte[] bpmnBytes = null;

		//流程XML 实例
		BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);

		bpmnBytes = new BpmnXMLConverter().convertToXML(model);
		String processName = modelData.getName() + ".bpmn20.xml";

		Deployment deployment =
				repositoryService.createDeployment()
						.name(modelData.getName()).addString(processName, new String(bpmnBytes, "UTF-8"))
						.deploy();



		ProcessDefinition processDefinition=repositoryService.createProcessDefinitionQuery()
				.deploymentId(deployment.getId())
				.singleResult();

		//InputStream in =  repositoryService.getProcessDiagram();



	}

	@Test
	public void testBmpn(){
		ProcessDefinitionEntity processDefinitionEntity = null;
		processDefinitionEntity = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
				.getDeployedProcessDefinition("123");
		List<ActivityImpl> activitiList = processDefinitionEntity.getActivities();

		ActivityImpl activityNode = processDefinitionEntity.findActivity("act_node_id");

//		ExecutionEntity execution = (ExecutionEntity) runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
//		//当前流程节点Id信息
//		String activitiId = execution.getActivityId();

		BpmnModel bpmnModel = repositoryService.getBpmnModel("123");
		// 节点ID 获取元素
		FlowElement element = bpmnModel.getFlowElement("234");
		FlowNode flowNode = (FlowNode) element;

		// 连接线 接入
		List<SequenceFlow>  sequenceFlowIn = flowNode.getIncomingFlows();
		// 连接线 出口
		List<SequenceFlow>  sequenceFlowsOut = flowNode.getOutgoingFlows();

		// 获取EL 表达式
		sequenceFlowsOut.get(0).getConditionExpression();
	}

	/**
	 * 根据key和value判断el表达式是否通过信息
	 *
	 * @param el           el表达式信息
	 * @param conditionMap el表达式传入值信息
	 * @return
	 */
	public static boolean isCondition(String el, Map<String, Object> conditionMap) {
		ExpressionFactory factory = new ExpressionFactoryImpl();
		SimpleContext context = new SimpleContext();
		for (String key : conditionMap.keySet()) {
			context.setVariable(key, factory.createValueExpression(conditionMap.get(key), String.class));
		}
		ValueExpression e = factory.createValueExpression(context, el, boolean.class);
		return (Boolean) e.getValue(context);
	}



}
