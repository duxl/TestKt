package a.b.c.kt.协程

import kotlinx.coroutines.*

/**
 * 1、使用 runBlocking 协程构建器将 main 函数转换为协程
 * 2、使用launch开启一个新协程
 * 3、所有子协程结束后，主协程runBlocking才会结束
 *
 * launch相当于异步、withContext相当于同步
 */
fun main() = runBlocking {
    printThread("1、runBlocking中打印")
    // launch开启一个新的协程，与其它代码异步执行
    launch/*(Dispatchers.Default)*/ {
        delay(1000)
        printThread("3、launch中打印")
    }
    printThread("2、launch代码块后打印")

    // withContext会启动新协程，并挂起当前线程直至withContext返回
    val result = withContext(Dispatchers.IO) {
        delay(2000)
        printThread("4、withContext中打印")
        System.currentTimeMillis()
    }
    // 需要withContext执行返回后才会执行下面的代码
    printThread("5、runBlocking结束")
    printThread("6、withContext协程中执行结果：$result")
}