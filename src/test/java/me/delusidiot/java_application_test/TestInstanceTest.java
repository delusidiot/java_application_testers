package me.delusidiot.java_application_test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestInstanceTest {
    int value = 0;

    @DisplayName("first test")
    @Test
    void firstTesting(){
        System.out.println(this);
        System.out.println("value = " + value++);
    }

    @DisplayName("second test")
    @Test
    void secondTesting(){
        System.out.println(this);
        System.out.println("value = " + value++);
    }
    // 본래 JUnit은 테스트간의 의존성을 없애기 위해 서로다른 인스턴스를 생성한다.
    // BeforeAll, AfterAll은 원래 static 메소드여야했지만 PER_CLASS이면 일반 메소드여도 된다.
}
