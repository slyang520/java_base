package com.slyang.test.juc.queue.blockingquene;

import java.util.concurrent.*;

public class BlockingQueueTest {

    public static void main(String[] args) throws Exception {

        // 有界 的阻塞队列
        BlockingQueue queue = new ArrayBlockingQueue(1024);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }

}
