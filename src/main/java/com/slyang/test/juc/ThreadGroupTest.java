package com.slyang.test.juc;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class ThreadGroupTest {

    public static void main(String[] args) {

        // 创建一个线程组
        ThreadGroup threadGroup = new ThreadGroup("Searcher");
        // 创建一个结果对象
        Result result = new Result();

        // 创建一个搜索任务，并且创建5个线程去运行这个任务
        SearchTask searchTask = new SearchTask(result);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(threadGroup, searchTask);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        // 输出线程组的信息
        System.out.printf("Number of Threads: %d\n", threadGroup.activeCount());
        System.out.printf("Information about the Thread Group\n");
        threadGroup.list(); // 将有关此线程组的信息打印到标准输出。

        Thread[] threads = new Thread[threadGroup.activeCount()]; // 返回此线程组中活动线程的估计数。
        threadGroup.enumerate(threads); // 把此线程组及其子组中的所有活动线程复制到指定数组中。
        for (int i = 0; i < threadGroup.activeCount(); i++) {
            System.out.printf("Thread %s: %s\n", threads[i].getName(), threads[i].getState());
        }

        // 等待线程结束
        waitFinish(threadGroup);

        // 中断线程组中的所有线程
        threadGroup.interrupt();

    }

    /**
     * 等待线程组中的一个线程结束
     *
     * @param threadGroup 线程组
     */
    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount() > 9) { // 如果线程组中的活动线程数大于9个，当前调用线程就休眠1秒，直到线程数小于9个
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 结果类用于存储搜索结果
     */
    public static class Result {
        /**
         * 完成任务的线程名
         */
        private String name;

        /**
         * 获取完成任务的线程名
         * @return  完成任务的线程名
         */
        public String getName() {
            return name;
        }

        /**
         * 设置完成任务的线程名
         * @param name 完成任务的线程名
         */
        public void setName(String name) {
            this.name = name;
        }
    }


    public static class SearchTask implements Runnable {

        /**
         * 如果线程完成了任务，并且没有中断，就存储线程的名字。
         */
        private Result result;

        /**
         * 构造函数
         *
         * @param result 结果对象
         */
        public SearchTask(Result result) {
            this.result = result;
        }

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            System.out.printf("Thread %s: Start\n", name);
            try {
                doTask();
                result.setName(name);
            } catch (InterruptedException e) {
                System.out.printf("Thread %s: Interrupted\n", name);
                return;
            }
            System.out.printf("Thread %s: End\n", name);
        }

        /**
         * 模拟搜索操作
         *
         * @throws InterruptedException 中断异常
         */
        private void doTask() throws InterruptedException {
            Random random = new Random((new Date()).getTime());
            int value = (int) (random.nextDouble() * 100);
            System.out.printf("Thread %s: %d\n", Thread.currentThread().getName(), value);
            TimeUnit.SECONDS.sleep(value);
        }
    }

}
