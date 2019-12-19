package com.slyang.test.gcCase;

// https://blog.csdn.net/u011767040/article/details/49180973
public class ReferenceCountGc {

    public Object instance;
    private static final int _1MB = 1024*1024;


    // 可以打印GC的简要信息
    //-XX:+PrintGC
    //-verbose:gc   // 2者效果一样
    
    // 打印GC详细信息
    // -XX:+PrintGCDetails

    // 打印CG发生的时间戳
    // -XX:+PrintGCTimeStamps

    // 指定GC log的位置
    // -Xloggc:log/gc.log

    // 每一次GC前和GC后，都打印堆信息
    // -XX:+PrintHeapAtGC

    // 监控类的加载
    // -XX:+TraceClassLoading

    // -XX:+PrintClassHistogram


    // 堆内存 的分配参数
    // -Xmx –Xms：指定最大堆和最小堆
    // -Xmx20m -Xms5m
    // 问题1： -Xmx（最大堆空间）和 –Xms（最小堆空间）应该保持一个什么关系，可以让系统的性能尽可能的好呢？

    // -XX:NewSize 新生代  初始值大小
    // -XX:MaxNewSize  新生代空间大小最大值

    // -XX:PermSize 永久代
    // -XX:MaxPermSize 永久代的最大值

    public static void main(String[] arg){

        ReferenceCountGc obj1 = new  ReferenceCountGc();
        ReferenceCountGc obj2 = new  ReferenceCountGc();

        obj1.instance=obj2;
        obj2.instance=obj1;

        obj1 = null;
        obj2 = null;

        System.gc();

    }

    

}
