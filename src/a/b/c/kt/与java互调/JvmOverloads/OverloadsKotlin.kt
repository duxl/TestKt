package a.b.c.kt.与java互调.JvmOverloads

/**
 * @JvmOverloads修饰的方法参数如果有默认值，
 * 生成的java代码会自动生成重载方法
 */
@JvmOverloads
fun test1(name: String = "zhangSan", age: Int = 39, boy: Boolean = true) {
    println("name = ${name}, age = ${age}, boy = ${boy}")
}