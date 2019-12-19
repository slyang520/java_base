package com.slyang.test.juc.disruptor;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */
@BenchmarkMode({Mode.SampleTime})
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations=3, time = 5, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations=1,batchSize = 50)
@Threads(2)
@Fork(1)
@State(Scope.Benchmark)
public class DisruptorTest {


    Lock lock = new ReentrantLock();
    long i = 0;
    AtomicLong atomicLong = new AtomicLong(0);

    @Benchmark
    public void measureLock() {
        lock.lock();
        i++;
        lock.unlock();
    }

    @Benchmark
    public void measureCAS() {
        atomicLong.incrementAndGet();
    }

    @Benchmark
    public void measureNoLock() {
        i++;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(DisruptorTest.class.getSimpleName())
                .forks(0)
                .warmupIterations(0)
                .measurementIterations(2).measurementTime(TimeValue.valueOf("10ms")).threads(5)
                .build();

        new Runner(opt).run();
    }


}
