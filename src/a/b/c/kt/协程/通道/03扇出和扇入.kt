package a.b.c.kt.协程.通道

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 扇出：1个生产者对N个消费者
 * 闪入：N个生产者对1个消费者
 */
fun main() = runBlocking<Unit> {
    //testSin01()
    testSin02()
}

/**
 * 扇出
 */
suspend fun CoroutineScope.testSin01() {
    val receiveChannel = produce {
        repeat(10) {
            send(it)
            //delay(50)
        }
    }

    // 启动5个协程（消费者）
    repeat(5) {
        launch {
            for (item in receiveChannel) {
                printThread("repeat=$it, item=$item")
            }
        }
    }
    receiveChannel.cancel()  // 取消协程生产者从而将它们全部杀死

}

/**
 * 扇出
 */
suspend fun CoroutineScope.testSin02() {
    val sendChannel = actor<Int> {
        repeat(10) {
            delay(50)
            val value = receive()
            printThread("接收到数据：$value")
        }
    }

    // 启动5个协程（生产者）
    repeat(5) {
        launch {
            sendChannel.send(it)
        }
    }
    delay(1000)
    sendChannel.close()
}