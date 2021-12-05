package me.delusidiot.java_application_test.extension;

import me.delusidiot.java_application_test.FastTest;
import me.delusidiot.java_application_test.SlowTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

//@ExtendWith(FindSlowTestExtension.class) // 1. 선언적인 방법
public class ExtensionModelTest {
    // JUnit4의 확장모델은 @RunWith(Runner), TestRule, MethodRule
    // JUnit5 Extention으로 합쳐졌습니다.

    @SlowTest
    void slow_test() throws InterruptedException {
        Thread.sleep(1005L);
    }

    @Test
    void slow_test2() throws InterruptedException {
        Thread.sleep(1005L);
    }

    //2. 필드에서 정의하는 방법
    // FindSlowTestExtension에선 THRESHOLD값을 정의할 수 없다.
    // FindFastTestExtension으로 instance를 생성해서 적용하는 방법.
    @RegisterExtension
    static FindFastTestExtension findFastTestExtension = new FindFastTestExtension(1000L);

    @Test
    void fast_test(){

    }

    @FastTest
    void fast_test2(){

    }
    // 3. ServiceLoader를 사용해서 자동으로 등록하는 방법.
    // junit.jupiter.extensions.autodetection.enabled = true
    // Extension이 의존성에 들어있으면 자동으로 등록해줍니다.
    // 해당방법은 JUnit5 Guide를 살펴볼것.
}
