package a.b.c.kt.协程.通道

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.broadcast
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * ps:BroadcastChannel 已被弃用，取而代之的是 SharedFlow 和 StateFlow，并且不再受支持
 */
fun main() = runBlocking {
    //val broadcaster = BroadcastChannel<Int>(Channel.BUFFERED)
    val broadcaster = Channel<Int>().broadcast(3)
    GlobalScope.launch {
        repeat(3) {
            broadcaster.send(it)
        }
    }

    repeat(2) {x->
        GlobalScope.launch {
            val receiveChannel = broadcaster.openSubscription()
            receiveChannel.consumeEach {
                printThread("launch${x}接收到数据$it")
            }
        }
    }

    delay(1000)
}