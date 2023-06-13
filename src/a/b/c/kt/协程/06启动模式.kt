package a.b.c.kt.协程

import kotlinx.coroutines.*

fun main() = runBlocking {
    //test01()
    //test02()
    //test03()
    //test04()
    test05()
}

// Default ：协程创建后立即开始调度。在调度前如果协程被取消。将其直接进入取消相应的状态。
suspend fun test01() {
    val job = GlobalScope.launch(start = CoroutineStart.DEFAULT) {
        println("job start")
        delay(3000)
        println("job finish")
    }
    delay(1000)
    job.cancel()
    println("job cancel")
    // 本示例代码：协程将不会执行完，因为在执行中被取消了
}

// ATOMIC协程创建后，立即开始调度，协程执行到第一个挂起点之前不影响取消
suspend fun test02() {
    val job = GlobalScope.launch(start = CoroutineStart.ATOMIC) {
        println("job start")
        delay(1000)
        println("job doing")
        delay(1500)
        println("job finish")
    }
    job.cancel()
    println("job cancel")
    // 本示例代码：job doing不会被打印，因为job已启动就被cancel了，
    // 但是start肯定会打印，因为在第一个delay挂起函数之前
}

// 主动调协程的start、join或者await等函数时，才会开始调度，如果调度前就被取消。那么该协程将直接进入异常状态。
suspend fun test03() {
    val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
        println("job start")
    }

    delay(3000)
    println("开始调用协程的start方法")
    job.start()
}

// 协程创建后理解在当前函数调用栈中被执行
suspend fun test04() {
    val job = GlobalScope.launch(context = Dispatchers.IO, start = CoroutineStart.UNDISPATCHED) {
        println("UNDISPATCHED立即在当前线程中执行，所以这里指定IO不起作用，也会在当前线程中执行：${Thread.currentThread().name}")
    }
    job.join()
}

// 指定线程或线程池名称，调试特别有用
suspend fun test05() {
    val job1 = GlobalScope.launch(newSingleThreadContext("myThread")) {
        println("使用newSingleThreadContext：${Thread.currentThread().name}")
    }
    job1.join()

    val job2 = GlobalScope.launch(newFixedThreadPoolContext(3, "myThreadPoll")) {
        println("使用newFixedThreadPoolContext：${Thread.currentThread().name}")
    }
    job2.join()
}