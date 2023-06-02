package a.b.c.kt.与java互调.JvmStatic

class StaticKotlin {

    companion object {

        // @JvmField修饰的字段Java可以像静态字段一样调用
        @JvmField
        val name = "zs"

        // 没有修饰，这个字段需要使用companion方法
        val age = 19

        // @JvmStatic修饰的方法java可以像静态方法一样调用
        @JvmStatic
        fun sayHello() {
            println("hello")
        }

        // 没有修饰，这个方法需要使用companion方法
        fun say() {
            println("I·m say method")
        }
    }
}