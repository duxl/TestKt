package a.b.c.kt.基础部分

fun main() {
    val `姓名` = "张三"
    println(`姓名`)
    `is`()
}

// is在kotlin中是关键词，要使用必须使用反引号
fun `is`() {
    println("我是is方法")
}