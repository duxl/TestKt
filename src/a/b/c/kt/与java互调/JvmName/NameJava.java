package a.b.c.kt.与java互调.JvmName;

public class NameJava {

    public static void main(String[] args) {
        // MyKotlin是通过@file:JvmName指定的kt生成的java文件名
        MyKotlin.sayHello("zhangSan");

        // 没有使用JvmName，NameKotlin2.kt默认生成的java文件是NameKotlin2Kt.java
        NameKotlin2Kt.sayHello2("lisi");
    }
}
