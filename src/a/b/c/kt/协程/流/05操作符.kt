package a.b.c.kt.协程.流

import a.b.c.kt.协程.printThread
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    //testOptionMap()
    //testOptTransform()
    //testOptTake()
    //testOptReduce()
    //testOptZip()
    //testOptFlatMapConcat()
    //flatMapMerge()
    flatMapLast()
}

// map操作符
private suspend fun testOptionMap() {
    (0..5).asFlow()
            .map {
                delay(100)
                "E$it" // 将Int转成String
            }
            .collect {
                printThread("collect: $it")
            }
}

// transform将一个元素转换成多个元素emit出去
private suspend fun testOptTransform() {
    listOf("hello", "kotlin").asFlow()
            .transform {
                it.forEach { c ->
                    emit(c)
                }
            }.collect {
                printThread("collect: $it")
            }
}

// 使用take（限长）操作符，只取流中指定个数的元素
private suspend fun testOptTake() {
    flow {
        repeat(10) {
            delay(100)
            printThread("emit: $it")
            emit(it)
        }
    }
            .take(2)
            .collect {
                printThread("collect: $it")
            }
}

// reduce末端操作符号求1～5只和（collect也是末端操作法）
private suspend fun testOptReduce() {
    // reduce操作符号的特点：
    // 1、第一次将0号元素和1号元素传递给回调函数（lambda）
    // 2、第二次将第一次运算的结果和2号元素传递给回调函数（lambda）
    val result = (1..5).asFlow()
            .reduce { accumulator, value ->
                printThread("accumulator: $accumulator, value=$value")
                accumulator + value
            }
    println("reduce result: $result")
}

// zip组合操作符
private suspend fun testOptZip() {
    val flow01 = (1..5).asFlow()
    val flow02 = flowOf("A", "B", "C")

    flow01.zip(flow02) { x, y ->
        "$x$y"
    }.collect {
        printThread("collect: $it")
    }
}

// 展平流-flatMapConcat
private suspend fun testOptFlatMapConcat() {
    // 将flow里面的每一个元素转换成flow，并展开连接成一个流，跟transform操纵符有点类似
    // flatMapConcat按照原始 Flow 中元素的顺序逐个合并新的 Flow
    flowOf(1, 2, 3)
            .onEach { delay(100) }
            .flatMapConcat {
                flow {
                    emit("$it-a")
                    emit("$it-b")
                }.onEach {
                    delay(400)
                }
            }.collect {
                printThread("collect: $it")
            }
}

// 展平流-flatMapMerge(flatMapConcat类似，只不过merge是并发地)
private suspend fun flatMapMerge() {
    flowOf(1, 2, 3)
            .onEach { delay(100) }
            .flatMapMerge {
                flow {
                    emit("$it-a")
                    emit("$it-b")
                }.onEach {
                    delay(400)
                }
            }.collect {
                printThread("collect: $it")
            }
}

// flatMapLast合并最后的流
private suspend fun flatMapLast() {
    // 这里外层的流耗时100*3就emit完毕
    // 内层的流在400才开始emit
    // 使用flatMapLast的结果是，使用外层最后的emit元素和内层的元素结合
    flowOf(1, 2, 3)
            .onEach { delay(100) }
            .flatMapLatest {
                flow {
                    emit("$it-a")
                    emit("$it-b")
                }.onEach {
                    delay(400)
                }
            }.collect {
                printThread("collect: $it")
            }
}