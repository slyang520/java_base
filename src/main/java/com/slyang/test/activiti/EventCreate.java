package com.slyang.test.activiti;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;


@Component
public class EventCreate implements TaskListener {

	@Override
	public void notify(DelegateTask delegateTask) {

		delegateTask.addCandidateUser("manager_userId");
		//delegateTask.setAssignee("manager_userId");

	}

}
