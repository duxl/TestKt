package a.b.c.kt.与java互调.Top;

public class TopJava {

    public static void main(String[] args) {
        // java中调用kt的顶层方法
        // 在kt中使用@file:JvmName可以指定生成的java类名
        KtUtils.sayHello("kotlin");
    }
}
