package a.b.c.kt.与java互调.JvmStatic

object ObjectKotlin {

    @JvmStatic
    fun test1(){
        println("ObjectKotlin.test1")
    }

    fun test2(){
        println("ObjectKotlin.test2")
    }
}