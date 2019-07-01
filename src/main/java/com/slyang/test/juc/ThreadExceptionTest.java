package com.slyang.test.juc;

public class ThreadExceptionTest {

    public static void main(String[] args) {

        Task task = new Task(); // 创建一个任务
        Thread thread = new Thread(task); // 创建一个线程
        thread.setUncaughtExceptionHandler(new ExceptionHandler()); // 设置线程的异常处理器
        thread.start();

        try {
            thread.join(); // 等待线程完成
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("Thread has finished\n");
        
    }

    /**
     * 异常处理类，处理线程中抛出的未捕获的异常
     */
    public static class ExceptionHandler implements Thread.UncaughtExceptionHandler {
        /**
         * 处理线程中抛出的未捕获的异常
         * @param t 招聘异常的线程
         * @param e 抛出的异常
         */
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.printf("An exception has been captured\n");
            System.out.printf("Thread: %s\n", t.getId());
            System.out.printf("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
            System.out.printf("Stack Trace: \n");
            e.printStackTrace(System.out);
            System.out.printf("Thread status: %s\n", t.getState());
        }
    }

    public static class Task implements Runnable {
        @Override
        public void run() {
            System.out.printf("Thread begin\n");
            // 下面的语句会异常
            int number = Integer.parseInt("TTT");

            System.out.printf("Thread has die\n");

        }
    }


}
