package a.b.c.kt.协程

import kotlinx.coroutines.*

fun main() = runBlocking {
    //testCancel01()
    //testCancel02()
    //testCancel03()
    //testCancel04()
    //testCancel05()
    try {
        // 超时自动取消
        testCancel06()
    } catch (e: TimeoutCancellationException) {
        println("超时cancel")
        e.printStackTrace()
    }
    //testCancel07()
    //testCancel08()
}

// 打印3次后正常取消
suspend fun testCancel01() {
    val job = GlobalScope.launch {
        repeat(100_00) {
            delay(1000) // 这里有delay挂起函数，所以可以正常取消
            println("repeat $it")
        }
    }
    delay(3500)
    job.cancel()
    job.join()
    //job.cancelAndJoin()
}

// 协程的取消是 协作 的。一段协程代码必须协作才能被取消。
// 所有 kotlinx.coroutines 中的挂起函数都是 可被取消的 。
// 它们检查协程的取消， 并在取消时抛出 CancellationException。
// 然而，如果协程正在执行计算任务，并且没有检查取消的话
//
// ps:这里可以理解为，如果协程体中的代码没有被挂起，而是一直在
// 密集计算运行，那么不能被取消，如果里面有delay函数就可以正常取消
suspend fun testCancel02() {
    val startTime = System.currentTimeMillis()
    val job = GlobalScope.launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // 一个执行计算的循环，只是为了占用 CPU
            // 每秒打印消息两次
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // 等待一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消一个作业并且等待它结束
    println("main: Now I can quit.")
}

suspend fun testCancel03() {
    val startTime = System.currentTimeMillis()
    val job = GlobalScope.launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // 可以被取消的计算循环(调用cancel后isActive=false)
            // 每秒打印消息两次
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // 等待一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消一个作业并且等待它结束
    println("main: Now I can quit.")
}

// 在 finally 中释放资源
suspend fun testCancel04() {
    val job = GlobalScope.launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L) // 这里有delay挂起函数，所以可以正常取消
            }
        } finally {
            println("job: I'm running finally（始终会被执行）")
        }
    }
    delay(1300L) // 延迟一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消该作业并且等待它结束
    println("main: Now I can quit.")
}

suspend fun testCancel05() {
    val job = GlobalScope.launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("job: I'm running finally")
                delay(1000L)
                println("job: And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // 延迟一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消该作业并等待它结束
    println("main: Now I can quit.")
}

// 超时自动取消
suspend fun testCancel06() {
    /*
    // withTimeout 超时后报异常
    val result = withTimeout(1500) {
        repeat(1000) {
            println("waiting $it")
            delay(500)
        }
        "ok"
    }
    println("result=$result")*/

    // withTimeoutOrNull超时后返回null
    val result = withTimeoutOrNull(1000) {
        repeat(1000) {
            println("waiting $it")
            delay(500)
        }
        "ok"
    }
    println(result)
}

// coroutineScope作用域下，一个协程异常了，其它兄弟协程也会被取消
suspend fun testCancel07() {
    GlobalScope.launch {
        val job1 = launch {
            delay(1000)
            throw RuntimeException("job1异常了")
            println("job1")
        }
        val job2 = launch {
            delay(2000)
            println("job2")
        }
    }.join()
}

// supervisorScope作用域下，一个协程异常了，不影响其它兄弟协程
suspend fun testCancel08() {
    supervisorScope {
        val job1 = launch {
            delay(1000)
            throw RuntimeException("job1异常了")
            println("job1")
        }
        val job2 = launch {
            delay(2000)
            println("job2正常结束")
        }
    }
}