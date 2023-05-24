package a.b.c.kt.类_接口_枚举.委托

var PI: Float = 3.14f

/**
 * 委托给另外一个属性，两个属性的访问和设置是同步一致的
 */
class MyClass {
    // 1⃣️当前类的属性委托给使用 this::
    @Deprecated(message = "name属性已弃用", replaceWith = ReplaceWith("newName"))
    var name by this::newName
    var newName = "zs"

    // 2⃣️委托给顶层属性使用::
    var pi: Float by ::PI
}

fun main() {
    val myCls = MyClass()

    // 1⃣️当前类的属性委托给使用 this::
    println(myCls.name)
    myCls.name = "lisi"
    println(myCls.name)
    myCls.newName = "wangwu"
    println(myCls.name)

    // 2⃣️委托给顶层属性使用::
    PI = 3.1415f
    println(myCls.pi)

}