package a.b.c.kt.协程.通道

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

/**
 * 多路复用select：
 * 本地和网络，哪个返回快就取哪个；
 * 没有被 select，任务不会被取消。
 */
fun main() = runBlocking {
    //test01()
    test02()
}


/**
 * 哪个快就取哪个
 */
suspend fun test01() {
    GlobalScope.launch {
        val result = select {
            getFromLocal().onAwait { it }
            getFromNetwork().onAwait { it }
        }
        println("result: $result")
    }.join()
}

/**
 * 没有被 select，任务不会被取消
 * 参考链接：https://juejin.cn/post/7664879818412834826
 */
suspend fun test02() = coroutineScope {
    // 待完成的任务
    val tasks = mutableListOf(getFromLocal(), getFromNetwork())

    // 获取最快返回的结果
    val result1 = select { tasks.map { d -> d.onAwait { it } } }
    println("result1=$result1")

    // 将已经完成的任务移除
    if(result1.first == 1) {
        tasks.removeAt(0)
    } else {
        tasks.removeAt(1)
    }

    // 获取第二快返回的结果
    val result2 = select { tasks.map { d -> d.onAwait { it } } }
    println("result2=$result2")

    // 取消未完成的任务
    // 已经完成的 Deferred 再调用 cancel() 不会有问题；尚未完成的请求则不会继续浪费资源
    tasks.forEach { it.cancel() }

}


// 模拟本地数据
fun CoroutineScope.getFromLocal() = async(Dispatchers.IO) {
    printThread("doing getFromLocal...")
    delay(500)
    //printThread("return getFromLocal...")
    1 to "local value"
}

// 模拟网络数据
fun CoroutineScope.getFromNetwork() = async(Dispatchers.IO) {
    printThread("doing getFromNetwork...")
    delay(800)
    //printThread("return getFromLocal...")
    2 to "network value"
}