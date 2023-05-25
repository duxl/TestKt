package a.b.c.kt.协程

import kotlinx.coroutines.*

/**
 * runBlocking是普通阻塞函数
 * coroutineScope 在当前线程中挂起
 * withContext 在指定调度器中挂起
 * launch 开启新协程（理解成新开线程）
 */
fun main() = runBlocking {

    // runBlocking是阻塞函数，即使指定调度器到IO中，
    // runBlocking代码块执行完毕后才能执行之后的代码
    runBlocking(Dispatchers.IO) {
        delay(1000)
        printThread("runBlocking阻塞")
    }
    printThread("runBlocking Over")
    println()

    // coroutineScope是挂起函数，虽然coroutineScope也需要代码块执行完毕后
    // 才能执行之后的代码，但它不会阻塞线程
    var result = coroutineScope {
        delay(1000)
        printThread("coroutineScope")
        "success"
    }
    printThread("coroutineScope Over result=$result")
    println()

    // 于coroutineScope类似，区别在于coroutineScope不能指定调度器，而withContext可以
    result = withContext(Dispatchers.IO) {
        delay(1000)
        printThread("withContext")
        "fail"
    }
    printThread("withContext Over result=$result")
    println()

    // launch完全就是新开一个协程（相当于新开了一个线程）
    launch {
        delay(1000)
        printThread("launch")
    }
    printThread("launch Over")

}