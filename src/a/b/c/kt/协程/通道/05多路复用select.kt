package a.b.c.kt.协程.通道

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select

/**
 * 多路复用select：
 * 本地和网络，哪个返回快就取哪个
 */
fun main() = runBlocking {
    GlobalScope.launch {
        val result = select {
            getFromLocal().onAwait{it}
            getFromNetwork().onAwait{it}
        }
        println("result: $result")
    }.join()
}

fun CoroutineScope.getFromLocal() = async(Dispatchers.IO) {
    printThread("doing getFromLocal...")
    delay(500)
    "this is local value"
}

fun CoroutineScope.getFromNetwork() = async(Dispatchers.IO) {
    printThread("doing getFromNetwork...")
    delay(800)
    "this is network value"
}