package com.slyang.test.juc.queue;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> unbounded = new LinkedBlockingQueue<String>();
        // 有界
        BlockingQueue<String> bounded   = new LinkedBlockingQueue<String>(1024);

        bounded.put("Value");

        String value = bounded.take();

        BlockingDeque b;
    }

}
