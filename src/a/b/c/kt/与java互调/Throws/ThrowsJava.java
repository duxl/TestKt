package a.b.c.kt.与java互调.Throws;

import java.io.IOException;

public class ThrowsJava {

    public static void main(String[] args) {
        try {
            ThrowsKotlinKt.testException2();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ThrowsKotlinKt.testException1();
    }
}
