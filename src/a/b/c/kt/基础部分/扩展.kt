package a.b.c.kt.基础部分

// 扩展属性
inline val Int.double: Int
    get() {
        // 返回当前数字的两倍
        return this * 2
    }

// 扩展方法
inline fun Int.half(): Float {
    // 返回当前数字的一半
    return this / 2f
}

fun main() {
    println(15.double)
    println(15.half())
}