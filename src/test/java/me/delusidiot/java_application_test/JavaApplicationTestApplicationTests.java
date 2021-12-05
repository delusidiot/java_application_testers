package me.delusidiot.java_application_test;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@SpringBootTest(properties = {
        "test.env=LOCAL"
})
class JavaApplicationTestApplicationTests {

    @Test
    @DisplayName("테스트1")
    void create_new_test(){
        System.out.println("Test1 start!");
    }

    @Test
    @DisplayName("테스트2")
    void create_new_test2(){
        System.out.println("Test2 start!");
    }

    @BeforeAll
    static void beforeAll(){
        System.out.println("BeforeAll");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("AfterAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("BeforeEach");
    }

    @AfterEach
    void afterEach() {
        System.out.println("AfterEach");
    }

}
