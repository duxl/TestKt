package a.b.c.kt.与java互调.JvmField

class FieldKotlin {

    var name: String = "zhangSan"

    /**
     * 使用JvmField修饰的字段，java调用的时候可以不通过get和set访问
     */
    @JvmField
    var age: Int = 18
}