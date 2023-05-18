package a.b.c.kt.类_接口_枚举

/**
 * 数据类特点：
 * 1、至少带一个参数的主构造函数
 * 2、主构造函数桉树必须是var或val
 * 3、不能用abstract、open、sealed、inner修饰符
 */
data class DataClass(var flag: Int)

fun main() {
    println(DataClass(10))
}