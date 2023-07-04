package a.b.c.kt.协程.通道

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.selects.select

/**
 * 多路复用
 * channel哪个最先接收到，就receive哪个
 */
fun main() = runBlocking<Unit> {
    val channel01 = Channel<Int>()
    val channel02 = Channel<Int>()

    val job01 = GlobalScope.launch {
        delay(200)
        channel01.send(1)
        channel01.close()
    }

    val job02 = GlobalScope.launch {
        delay(500)
        channel02.send(2)
        channel02.close()
    }

    val job03 = GlobalScope.launch {
        val result = select<Int> {
            channel01.onReceive { it }
            channel02.onReceive { it }
        }
        printThread("result: $result")
    }

    joinAll(job01, job02, job03)
}