package com.slyang.test.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MyCallable {

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		HCallable mc = new HCallable();

		FutureTask<String> ft = new FutureTask<>(mc);
		Thread thread = new Thread(ft);

		System.out.println("1111:   "+System.currentTimeMillis());
		thread.start();
		System.out.println("2222:   "+System.currentTimeMillis());

		System.out.println(ft.get());
		System.out.println("3333:   "+System.currentTimeMillis());


	}

	public static class HCallable implements Callable<String> {

		@Override
		public String call() throws Exception {
			Thread.sleep(2000);
			return "Hello, World!";
		}

	}

}
