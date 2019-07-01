package com.slyang.test.juc;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ThreadInterruptTest {


    public static void main(String[] args) throws InterruptedException {

        Thread task = new PrimeGenerator();
        task.start(); // 启动质数生成线程

        try {
            TimeUnit.SECONDS.sleep(2); // 主线程休眠2s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.interrupt(); // 质数生成线程中断


        TimeUnit.SECONDS.sleep(2); // 主线程休眠2s


        // Q.2

        // 创建一个文件时间运行对象，并且将其放入一个线程对象中
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);

        // 开始线程
        thread.start();
        try {
            // 等待五秒
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断线程
        thread.interrupt();
    }


    public static class PrimeGenerator extends Thread {
        @Override
        public void run() {
            long number = 1L;

            while (true) {
                // 对每个数字，计算它是不是一个质数，如果是的话就打印到控制台。
                if (isPrime(number)) {
                    System.out.printf("Number %d is Prime\n", number);
                }

                // 当被中断时，输出一条消息，并且退出方法
                if (isInterrupted()) {
                    System.out.printf("The Prime Generator has been Interrupted\n");
                    
                    return;
                }
                number++;
            }
        }

        /**
         * 判断一个数是否是质数
         *
         * @param number 待判断的数
         * @return true是质数，false不是质数
         */
        private boolean isPrime(long number) {
            if (number <= 2) {
                return true;
            }

            for (long i = 2; i < number; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }


    // 文件定时类，每隔一秒钟将实际的时间输出
    public static class FileClock implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                System.out.printf("%s\n", new Date());
                try {
                    // 休眠一秒
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    // 当线程被中断时，释放或者关闭线程正在使用的资源。
                    System.out.println("The FileClock has been interrupted");
                    return; // 发生异常就跳出
                }
            }
        }
    }


}
