package com.slyang.test.juc;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class ThreadHelp {

    //  wait()方法的作用是将当前运行的线程挂起（即让其进入阻塞状态），直到notify或notifyAll方法来唤醒线程.
    // wait(long timeout)，该方法与wait()方法类似，
    // 唯一的区别就是在指定时间内，如果没有notify或notifAll方法的唤醒，也会自动唤醒。


    // CountDownLatch  是一个同步的辅助类，允许一个或多个线程一直等待，直到其它线程完成它们的操作。
    @Test
    public void testCountDownLatch(){
        final CountDownLatch countDownLatch = new CountDownLatch(5);

        System.out.println("TASK start");
        new Thread(() -> {
            try {
                // 这里调用的是await()不是wait()
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("...5 个 TASK end ");
        }).start();

        // 其他TASK线程启动
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                System.out.println("WORK END ");
                countDownLatch.countDown();
            }).start();
        }


    }


    //    CyclicBarrier允许一组线程互相等待，直到到达某个公共屏障点。叫做cyclic
    //    是因为当所有等待线程都被释放以后，CyclicBarrier可以被重用(对比于CountDownLatch是不能重用的)
    @Test
    public void testCyclicBarrier(){

        final CyclicBarrier CyclicBarrier = new CyclicBarrier(2);
        for (int i = 0; i < 2; i++) {

            new Thread(() -> {

                String name = Thread.currentThread().getName();
                if (name.equals("Thread-0")) {
                    name = "3y";
                } else {
                    name = "女朋友";
                }
                System.out.println(name + "到了体育西");
                try {

                    // 两个人都要到体育西才能发朋友圈
                    CyclicBarrier.await();
                    
                    // 他俩到达了体育西，看见了对方发了一条朋友圈
                    //
                    System.out.println("跟" + name + "去夜上海吃东西~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


    @Test
    public void testSemaphore() throws InterruptedException {
//        Semaphore(信号量)实际上就是可以控制同时访问的线程个数，它维护了一组**"许可证"**。
//        当调用acquire()方法时，会消费一个许可证。如果没有许可证了，会阻塞起来
//        当调用release()方法时，会添加一个许可证。
//        这些"许可证"的个数其实就是一个count变量罢了~

        // 假设有50个同时来到酸奶店门口
        int nums = 50;

        // 酸奶店只能容纳10个人同时挑选酸奶
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < nums; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    // 有"号"的才能进酸奶店挑选购买
                    semaphore.acquire();

                    System.out.println("顾客" + finalI + "在挑选商品，购买...");

                    // 假设挑选了xx长时间，购买了
                    Thread.sleep(1000);

                    // 归还一个许可，后边的就可以进来购买了
                    System.out.println("顾客" + finalI + "购买完毕了...");
                    semaphore.release();



                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        }


        Thread.sleep(15_000);



    }




}
