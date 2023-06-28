package a.b.c.kt.协程.通道

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.*

fun main() {
    //testSimpleChannel01()
    //testSimpleChannel02()
    //testSimpleChannel03()
    //testSimpleChannel04()
    testSimpleChannel05()
}

/**
 * 使用channel发送和接收数据：
 * 在线程中发送3个元素，在main中接收3个元素
 * send函数是挂起函数，当达到缓冲个数未被接收时，send将被挂起
 * receive函数是挂起函数，当没有元素可以接收时，receive将被挂起
 */
private fun testSimpleChannel01() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch(Dispatchers.Default) {
        repeat(3) {
            printThread("send# $it")
            channel.send(it)
        }
    }


    repeat(3) {
        val value = channel.receive()
        printThread("receive value# $value")
    }
    printThread("---OVER---")
}

/**
 * 通道关闭与结束
 */
private fun testSimpleChannel02() = runBlocking<Unit> {
    val channel = Channel<Int>()
    launch(Dispatchers.IO) {
        for (i in 0..5) {
            channel.send(i)
        }
        // 发送完毕后，这里关闭通道
        channel.close()
    }

    // 如果通道没有close()的话，下面的循环会被挂起结束不了
    // 如果使用channel.receive()而不是for，即使没有close，只要没有send元素了程序就会结束
    for (item in channel) {
        printThread("receive#$item")
    }

    printThread("----Done----")
}

/**
 * 使用协程作用域的produce函数创建生产者，
 * 消费端使用consumeEach替代for
 */
private fun testSimpleChannel03() = runBlocking<Unit> {
    //val receiveChannel: ReceiveChannel<Int> = GlobalScope.produce<Int> {
    val receiveChannel: ReceiveChannel<Int> = produce<Int> {
        repeat(3) {
            printThread("send# $it")
            send(it)
        }
        close()
    }

    // 接收方式一
    /*
    for(item in receiveChannel) {
        printThread("receive# item")
    }*/


    // 接收方式二
    /*
    while (!receiveChannel.isClosedForReceive) {
        printThread("receive# ${receiveChannel.receive()}")
    }*/

    // 接收方式三
    receiveChannel.consumeEach {
        printThread("receive# $it")
    }
}

/**
 * 通道连接示例
 */
private fun testSimpleChannel04() = runBlocking<Unit> {
    // 一个不停产生数字的通道
    val numReceiveChannel = produce {
        repeat(100_00) {
            send(it)
        }
    }

    // 将数字通道转变成数字的平方通道的方法
    fun square(numChannel: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
        numChannel.consumeEach {
            send(it * it)
        }
    }

    // 连接通道
    val squareReceiveChannel = square(numReceiveChannel)

    // 取通道中的5个元素打印
    repeat(5) {
        val value = squareReceiveChannel.receive()
        printThread("receive: $value")
    }

    // 取消子协程
    coroutineContext.cancelChildren()
    printThread("Done")
}

/**
 * 使用协程作用域的actor函数创建消费者，
 * 生产端使用send将数据发送到消费端
 */
private fun testSimpleChannel05() = runBlocking<Unit> {
    /*val sendChannel: SendChannel<Int> = GlobalScope.actor<Int> {*/
    val sendChannel: SendChannel<Int> = actor<Int> {
        repeat(3) {
            val receive = receive()
            printThread("receive: $receive")
        }
    }

    launch {
        repeat(3) {
            printThread("send: $it")
            sendChannel.send(it)
        }
    }
    printThread("Over")
}