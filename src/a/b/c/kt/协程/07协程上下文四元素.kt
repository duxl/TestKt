package a.b.c.kt.协程

import kotlinx.coroutines.*
import java.lang.ArithmeticException

/**
 * 协程上下文由四元素构成，分别是：协程调度器、协程名字、Job、异常处理器
 */
fun main() {
    testCoroutineContext01()
    testCoroutineContext02()
}

private fun testCoroutineContext01() = runBlocking {
    launch(context = Dispatchers.Default) {
        printThread("testCoroutineContext01")
    }
}

private fun testCoroutineContext02() = runBlocking {
    launch(context = Dispatchers.IO + CoroutineName("MyName") + Job() + handler) {
        printThread("testCoroutineContext02")
        throw ArithmeticException("div by zero")
        printThread("testCoroutineContext02 Over")
    }.join() // 这些需要调用join异常才会抛出
}

private val handler by lazy {
    CoroutineExceptionHandler { coroutineContext, throwable ->
        printThread("捕获到异常：${throwable.message}#${coroutineContext}")
    }
}
