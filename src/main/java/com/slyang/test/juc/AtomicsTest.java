package com.slyang.test.juc;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子操作相关
 */
public class AtomicsTest {

//    实现全局自增id最简单有效的方式是什么？
// 
// java.util.concurrent.atomic包定义了一些常见类型的原子变量。
// 这些原子变量为我们提供了一种操作   单一变量无锁(lock-free)的线程安全(thread-safe)方式。

// 实际上该包下面的类为我们提供了类似volatile变量的特性，同时还提供了诸如boolean compareAndSet(expectedValue, updateValue)的功能。
// 不使用锁实现线程安全听起来似乎很不可思议，这其实是通过CPU的compare and swap指令实现的，
// 由于硬件指令支持当然不需要加锁了。
//

//    compare and swap    CAS

    // AtomicInteger and AtomicLong                                     A {int, long} value that may be updated atomically
    // AtomicReference                                                  An object reference that may be updated atomically
    // AtomicIntegerArray, AtomicLongArra, AtomicReferenceArray         An {long, int, object} array in which elements may be updated atomically.

    class Sequencer {

        private final AtomicLong sequenceNumber = new AtomicLong(0);
        private long sequenceNumber2 = 0;
        private volatile long sequenceNumber3 = 0;

        public long nextAtomic() {
            // sequenceNumber.getAndIncrement()  +1 获取先前的值
            // sequenceNumber.incrementAndGet()  +1 获取更新后的值
            sequenceNumber.incrementAndGet();
            return sequenceNumber.get();
        }

        public long nextAtomic2() {
            sequenceNumber2++;
            return  sequenceNumber2;
        }

        public long nextAtomic3() {
            sequenceNumber3++;
            return  sequenceNumber3;
        }

    }

    @Test
    public void helloAtomic() throws InterruptedException {
        Sequencer sequencer = new Sequencer();

        int loopLength=100000;

        Runnable execNext = () -> {
            for(int i=0;i<loopLength;i++){
                System.out.println(Thread.currentThread().getName()+"  nextAtomic    " + sequencer.nextAtomic());
                System.out.println(Thread.currentThread().getName()+"  nextAtomic2   " + sequencer.nextAtomic2());
                System.out.println(Thread.currentThread().getName()+"  nextAtomic3   " + sequencer.nextAtomic3());
            }
        };
        new Thread(execNext).start();
        new Thread(execNext).start();
        new Thread(execNext).start();


        Thread.sleep(1000*15);

        System.out.println("The expected value is "+loopLength*3);

    }

    //https://www.jianshu.com/p/882d0e2c3ea6



}
