package a.b.c.kt.协程

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 普通工程中添加coroutines的依赖：
 * Project Structure-》Modules-》Add-》Library-》From Maven
 * 输入地址：org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0-》点击OK
 *
 * https://www.kotlincn.net/docs/reference/coroutines/coroutines-guide.html
 */
fun main() {
    //test1()
    //test2()
    test3()
}

fun test1() {
    //  GlobalScope 启动协程的生命周期只受整个应用程序的生命周期限制
    GlobalScope.launch {
        printThread("协程中打印")
    }
    printThread("main中打印")
    // 这里需要延迟，不然主线程很有可能比协程提前结束
    Thread.sleep(1000) // 阻塞main函数
}

fun test2() {
    //  GlobalScope 启动协程的生命周期只受整个应用程序的生命周期限制
    GlobalScope.launch {
        printThread("协程中打印")
    }
    printThread("main中打印")
    runBlocking { // runBlocking会阻塞所在代码块的线程
        printThread("runBlocking中打印")
        delay(1000) // 挂起main函数
    }
}

fun test3() {
    // 延迟一段时间来等待另一个协程运行并不是一个好的选择
    // 使用join更加合理
    runBlocking {
        //  GlobalScope 启动协程的生命周期只受整个应用程序的生命周期限制
        val job = GlobalScope.launch {
            printThread("协程中打印")
        }
        printThread("main中打印")
        job.join() // 等待协程结束
        printThread("main中打印Over")
    }
}