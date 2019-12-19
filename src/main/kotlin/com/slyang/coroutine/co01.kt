package com.slyang.coroutine

/**
 * Description:
 * Created by slyang <slyang520@yeah.net>
 * Copyright (c) 2019, All Rights Reserved.
 */

import kotlinx.coroutines.*
import java.util.*

// Hello,   main
// World!   DefaultDispatcher-worker-2
fun main() {
    GlobalScope.launch { // 在后台启动一个新的协程并继续
        delay(1000L) //    非阻塞的等待 1 秒钟（默认时间单位是毫秒）
        println("World!   "+Thread.currentThread().name+  println(Date().time)) // 在延迟后打印输出
    }
    println("Hello,  " + Thread.currentThread().name+ println(Date().time)) // 协程已在等待时主线程还在继续
    Thread.sleep(2000L) // 阻塞主线程 2 秒钟来保证 JVM 存活
}