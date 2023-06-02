package a.b.c.kt.与java互调.JvmField;

public class FieldJava {

    public static void main(String[] args) {
        FieldKotlin fieldKotlin = new FieldKotlin();

        // 必须使用get访问kt中name
        System.out.println(fieldKotlin.getName());

        // 直接通过属性访问kt中的age
        System.out.println(fieldKotlin.age);
    }
}
