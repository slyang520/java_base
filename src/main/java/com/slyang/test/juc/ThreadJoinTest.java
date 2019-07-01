package com.slyang.test.juc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadJoinTest {

    public static void main(String[] args) {

        // 创建并启动数据源加载器
        DataSourcesLoader dsLoader = new DataSourcesLoader();
        Thread thread1 = new Thread(dsLoader, "DataSourceThread");
        thread1.start();

        // 创建并且启动网络连接加载器
        NetworkConnectionsLoader ncLoader = new NetworkConnectionsLoader();
        Thread thread2 = new Thread(ncLoader, "NetworkConnectionLoader");
        thread2.start();

        // 待待两个线程的任务完成
        try {
            thread1.join(); // Waits for this thread to die.
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 两个任务都完成后输出一条消息
        System.out.printf("Main: Configuration has been loaded: %s\n", new Date());


    }


    /**
     * 数据源加载器，模拟数据加载，它会休眠4s
     */
    public static class DataSourcesLoader implements Runnable {
        @Override
        public void run() {
            // 输出一条消息
            System.out.printf("Beginning data sources loading: %s\n",new Date());

            // 休眠4s
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出一条消息
            System.out.printf("End Data sources loading has finished: %s\n",new Date());
        }
    }


    /**
     * 网络连接加载器，模拟网络连接，它会休眠2s
     */
    public static class NetworkConnectionsLoader implements Runnable {
        @Override
        public void run() {
            // 输出一条消息
            System.out.printf("Begining network connections loading: %s\n",new Date());

            // 休眠2s
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 输出一条消息
            System.out.printf("End Network connections loading has finished: %s\n",new Date());
        }
    }


}
