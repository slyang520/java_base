package com.slyang.test.timer;

import org.junit.Test;
import org.quartz.*;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class quartzTest {

	@Test
	public void test1() throws SchedulerException, IOException {
		SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

		Scheduler sched = schedFact.getScheduler();

		sched.start();

		// define the job and tie it to our HelloJob class
		JobDetail job = newJob(HelloJob.class)
				.withIdentity("myJob", "group1")
				.usingJobData("jobSays", "Hello World!")  // 可以在该JOB中获取改数据 
				.usingJobData("myFloatValue", 3.141f)
				.build();

		// Trigger the job to run now, and then every 5 seconds
		Trigger trigger = newTrigger()
				.withIdentity("myTrigger", "group1")
				.startNow()
				.withSchedule(simpleSchedule()
						.withIntervalInSeconds(5)
						.repeatForever())
				.build();

		// Tell quartz to schedule the job using our trigger
		sched.scheduleJob(job, trigger);

		MethodInvokingJobDetailFactoryBean tes;
		CronTriggerFactoryBean co;
		SchedulerFactoryBean schedulerFactoryBean;

		System.in.read();
	}



}
