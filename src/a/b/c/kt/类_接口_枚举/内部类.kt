package a.b.c.kt.类_接口_枚举

// 1、普通的内部（嵌套）类就是嵌套关系，相互没有任何关联，内部类可以直接实例化
class OuterClass {
    val outStr = "我是外部类"
    fun outShow() {
        println("outShow")
    }

    class InnerClass {
        val innerStr = "我是内部类"
        fun innerShow() {
            println("innerShow")
        }
    }
}

// 2、inner作用内部类持有外部类的实例，可以直接访问外部类的属性和方法
class OuterClass2 {
    val outStr = "我是外部类"


    inner class InnerClass {
        fun show() {
            println("内部类直接访问外部类的属性：$outStr")
        }
    }
}

fun main() {
    // 普通内部内可以直接创建
    OuterClass.InnerClass().innerShow()

    // inner修饰的内部类需要通过外部类的实例才能创建内部类
    OuterClass2().InnerClass().show()
}