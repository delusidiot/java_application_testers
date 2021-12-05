package me.delusidiot.java_application_test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class GetTestPropertiesTest {
    // test만의 properties를 가지고와 사용할 수 있습니다.
    // test디렉터리 안의 resources의 properties는 classPath로 지정되어 있지 않기 때문에 IntelliJ에서 지정해 주어야합니다.
    // Project Structure -> Modules에서 Test Resources로 지정할 수 있습니다.
    @Test
    @Disabled
    void test(){
        System.out.println("test");
    }
}
