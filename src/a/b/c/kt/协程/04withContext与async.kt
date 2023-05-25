package a.b.c.kt.协程

import kotlinx.coroutines.*

/**
 * 多个withContext是串行执行
 * 多个async是并行执行
 */
fun main() = runBlocking {
    testWithContext()
    testAsync()

    /**
     * 在android中还有doAsync和uiThread的方式切换线程，只是跟协程没关系
     *
     *  doAsync {
     *         Log.e("TAG", " doAsync...   [当前线程为：${Thread.currentThread().name}]")
     *         uiThread {
     *             Log.e("TAG", " uiThread....   [当前线程为：${Thread.currentThread().name}]")
     *         }
     *     }
     */
}

// 串行任务
suspend fun testWithContext() {
    var beginTime = System.currentTimeMillis()
    var result1 = withContext(Dispatchers.Default) {
        add(2, 3)
    }
    var result2 = withContext(Dispatchers.Default) {
        add(5, 1)
    }
    var endTime = System.currentTimeMillis()
    println("withContext#result=[$result1,$result2], 耗时：${endTime - beginTime}")
}

// 并行任务
suspend fun testAsync() = runBlocking {
    var beginTime = System.currentTimeMillis()
    var job1 = async(Dispatchers.Default) {
        add(2, 3)
    }
    var job2 = async(Dispatchers.Default) {
        add(5, 1)
    }
    var result1 = job1.await() // await()挂起等待结果
    var result2 = job2.await() // await()挂起等待结果
    var endTime = System.currentTimeMillis()
    println("async#result=[${result1},${result2}], 耗时：${endTime - beginTime}")
}

suspend fun add(x: Int, y: Int): Int {
    delay(1000)
    return x + y
}