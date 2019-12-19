package com.slyang.coroutine

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */

import kotlinx.coroutines.*
import java.util.*

// Hello,   main         。。8756
// World!   DefaultDispatcher-worker-2      。。9758
fun main() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) //    非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!   "+Thread.currentThread().name+  println(Date().time)) // 在延迟后打印输出
    }
    println("Hello,  " + Thread.currentThread().name+ println(Date().time)) // 协程已在等待时主线程还在继续
    runBlocking {     // 但是这个表达式阻塞了主线程
        delay(2000L)  // ……我们延迟 2 秒来保证 JVM 的存活
    }
}