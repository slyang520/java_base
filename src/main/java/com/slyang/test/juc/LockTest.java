package com.slyang.test.juc;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

    // https://tech.meituan.com/2018/11/15/java-lock.html
    // https://github.com/CarpenterLee/JCRecipes/blob/master/markdown/synchronized_and_Reentrantlock.md

    // 锁

    //    Java为我们提供了内置锁(synchronized)和
    //                  显式锁(ReentrantLock)
    //                  两种同步方式

//    // synchronized关键字用法示例


//    当synchronized作用于普通方法是，锁对象是this；
//    public synchronized void add(int t){// 同步方法
//        this.v += t;
//    }
//
//    当synchronized作用于静态方法是，锁对象是当前类的Class对象；
//    public static synchronized void sub(int t){// 同步静态方法
//        value -= t;
//    }
//
//    当synchronized作用于代码块时，锁对象是synchronized(obj)中的这个obj。
//    public int decrementAndGet(){
//        synchronized(obj){// 同步代码块
//            return --v;
//        }
//    }


//    TicketLock、CLHlock和MCSlock
//    常见自旋锁


    @Test
    public void helloLock()  {
        ReentrantLock reentrantLock;
        
    }


}
