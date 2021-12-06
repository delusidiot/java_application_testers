package me.delusidiot.java_application_test;

import me.delusidiot.java_application_test.domain.Study;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

@SpringBootTest(properties = {
        "test.env=LOCAL"
})
class Run_according_to_conditions {

    @Value("${test.env}")
    private String testEnv;

    @Test
    @DisplayName("test.env가 LOCAL인 경우 스터디 만들기 테스트")
    void create_new_study() throws IllegalAccessException {
        System.out.println("test_env = " + testEnv);
        assumeTrue("LOCAL".equalsIgnoreCase(testEnv));

        Study actual = new Study(10);
        assertThat(actual.getLimit()).isGreaterThan(0);
    }

    @Test
    @DisplayName("test.env설정에 따른 테스트")
    void setting_according_to_test(){
        Assumptions.assumingThat("LOCAL".equalsIgnoreCase(testEnv), ()->{
            Study actual = new Study(10);
            assertThat(actual.getLimit()).isGreaterThan(0);
        });
        Assumptions.assumingThat("DEV".equalsIgnoreCase(testEnv), ()->{
            Study actual = new Study(100);
            assertThat(actual.getLimit()).isGreaterThan(10);
        });
    }

    @Test
    @DisplayName("Windows os에서만 테스트 실행")
    @EnabledOnOs(OS.WINDOWS)
    void windows_os_shouldRun() throws IllegalAccessException {
        System.out.println(System.getenv());
        System.out.println("windows os");
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(10);
    }

    @Test
    @DisplayName("Linus os에서만 테스트 실행")
    @EnabledOnOs(OS.LINUX)
    void linux_os_shouldRun() throws IllegalAccessException {
        System.out.println("Linux os");
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(10);
    }

    @Test
    @DisplayName("JRE version에따른 테스트 실행")
    @EnabledOnJre({JRE.JAVA_17, JRE.JAVA_11})
    void jre11_shouldRun() throws IllegalAccessException {
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(10);
    }

    @Test
    @DisplayName("환경변수에따른 테스트 실행")
    @EnabledIfEnvironmentVariable(named = "TEST_ENV", matches = "local")
    void environment_variable_shouldRun() throws IllegalAccessException {
        System.out.println("windows os");
        Study actual = new Study(100);
        assertThat(actual.getLimit()).isGreaterThan(10);
    }

}
