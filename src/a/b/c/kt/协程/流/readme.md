# [流的一些笔记](https://book.kotlincn.net/text/flow.html)

> 挂起函数可以异步的返回单个值，但是该如何异步返回多个计算好的值呢？这正是 Kotlin 流（Flow）的用武之地。

>  流是冷的，collect收集的时候才会emit发送。多次收集会重复发送

### 操作符

1. 转换 **transform** 将流中的每个元素处理转换重新emit发送，可以多次emit

2. 限长 **take** 在流触及相应限制的时候会将它的执行取消

3. 末端流操作符 **collect**、**toList**、**toSet**、**first**、**reduce**、**fold**

> reduce和fold都是归一操作法，将流中的元素通过归一计算，的到最后的结果，比如求和。
> fold与reduce的不同之处是可以设置初始值，reduce将第一个元素作为初始值

4. 流在调用它的协程（调用collect函数所在的）上下文中执行，流的发射emit称为上游，收集称为下游，**flowOn** 切换上游的上下文，launchIn切换下游的作用域

5. 缓冲 **buffer** 将速度快的上游缓冲起来给速度慢的下游慢慢处理，避免上游需等待下游处理完后才生产

6. **conflate** 下游处理较慢时跳过中间值，取上游当前最新的值 **（会处理完毕后再取最新值处理）**

7. **collectLatest** 下游还未处理完上游又发送了新值，下游丢弃前面的而处理新值 **（处理一半会丢弃旧值去处理新值）**

8. 合并 **zip** 将a,b两个流合并成一个流，a中的第一个元素与b中的第一个元素合并新的元素，第二个、第三个等元素以此类推，合并个数以a，b较小size为准

9. **combine** 与 **zip**都是合并流，但不同于zip之处是，`combine`的a，b两个流只要任意一个流中有新值，就会把这个新值与另一个流的当前值合并成一个新元素。如果另一个流还未产生第一个元素，那就不会开始合并。如果a流已经是最后一个元素，那始b流每产生的一个元素就和a的最后一个元素合并

10. 展平流 **flatMapConcat** 和 **flattenConcat**，包含流的流，也就是流的每一个元素也是个单独的流，使用这两个操作符可以开展成一个流。`flatMapConcat`是在生成流中的流的时候就展开(中间有`map`操作，即将流的普通元素变成新的流元素之后再展开)，而`flattenConcat`是将已经是流包含流展开。Concat是连接的意思，将流的元素`按顺序`连成一串

11. **flatMapMerge** 和 `flatMapConcat`有点类似。只不过在map的子流过程中，子流先emit的元素会发送到下游收集，这里的Merge`不一定`按顺行emit

12. **flatMapLatest** 此操作符号结合了`flatMapMerge`和`collectLatest`的特点

### 流异常

1. 上游和下游发生的异常，都可以在收集的时候使用try-catch来捕获

2. **catch** 过渡操作符也可以捕获异常，并可以在catch异常时emit元素。不过只能捕获上游异常，不能捕获下游异常。不过可以通过巧用onEach和无参的collect，并使用固定的onEach、catch、collect链式顺序，使catch也能捕获到onEach的异常
``` kotlin
flow.onEach {
        check(it < 2)
        println("onEach: $it")
    }.catch {
        println("异常了：${it.message}")
    }.collect()
```

3. 不能在构建器内部发射的时候try-catch（例如在`flow { ... } `中使用try-catch），这样会破坏对异常的透明，导致下游不知道发生了异常

### 流完成

使用finally`（try-finally）`和过渡操作符`onCompletion`都可以收集流的完成。`onCompletion`的主要优点是其 lambda 表达式的可空参数 Throwable 可以用于确定流收集是正常完成还是有异常发生

### 流取消

1. 流的收集collect是在协程中执行的，所以可以调用cancel（取消协程）来取消流

``` kotlin
fun main() = runBlocking {
    flow {
        (1..5).forEach {
            emit(it)
            println("emit $it")
        }
    }.collect {
        println("collect $it")
        if (it == 3) {
            // 调用cancel取消协程，变相的取消流
            cancel()
        }
    }
}
```

2. 出于性能原因，大多数繁忙流（在协程处于繁忙循环的情况下）不能取消，必须明确检测是否取消

``` kotlin
fun main() = runBlocking {
    (1..5).asFlow() // asFlow扩展来编写的繁忙循环，并且没有在任何地方暂停，那么就没有取消的检测
            .cancellable() // 这里调用cancellable明确可以取消
            .collect {
                println("collect $it")
                if (it == 3) {
                    // 调用cancel取消协程，变相的取消流
                    cancel()
                }
            }
}
```

等价于下面的手动检测代码

``` kotlin
fun main() = runBlocking {
    (1..5).asFlow() // asFlow扩展来编写的繁忙循环，并且没有在任何地方暂停，那么就没有取消的检测
            .onEach {
                coroutineContext.ensureActive() // 这里手动循环每个元素并检测协程的取消
            }
            .collect {
                println("collect $it")
                if (it == 3) {
                    // 调用cancel取消协程，变相的取消流
                    cancel()
                }
            }
}
```



