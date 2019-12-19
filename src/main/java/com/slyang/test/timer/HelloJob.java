package com.slyang.test.timer;

import org.quartz.*;

import java.util.concurrent.ForkJoinPool;

public class HelloJob implements Job {

	// 这个对象调度完成 会被释放掉
	// 可以用 JobDataMap 完成数据共享

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		System.err.println("Hello!  HelloJob is start.");

		ForkJoinPool forkJoinPool;

		JobKey key = context.getJobDetail().getKey();

		JobDataMap dataMap = context.getJobDetail().getJobDataMap();

		String jobSays = dataMap.getString("jobSays");
		float myFloatValue = dataMap.getFloat("myFloatValue");

		System.err.println("Instance "
				+ key + " of DumbJob says: "
				+ jobSays + ", and val is: "
				+ myFloatValue);

	}
	
}
