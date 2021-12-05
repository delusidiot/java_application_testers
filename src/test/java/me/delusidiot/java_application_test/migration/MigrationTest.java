package me.delusidiot.java_application_test.migration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MigrationTest {
    //1. JUnit5에선 JUnit4를 사용할 수 있습니다.
    // 사용하기 위해서 greadle에서 testImplementation 'org.junit.vintage:junit-vintage-engine'를 넣어주어야 합니다.
    // 기본적으로 @Rule을 지원하지 않습니다. junit-jupiter-migrationsupport 모듈이 제공하는 @EnableRuleMigrationSupport를 사용해야 몇가지 Rule을 사용할 수 있습니다.
    // - ExternalResource
    // - Verifier
    // - ExpectedException
    /*
    Category(Class) => Tag(String)
    RunWith Rule ClassRule => ExtendWith RegisterExtension
    Ignore => Disabled
    Before After... => BeforeEach AfterEach...
     */

    @Before
    public void before(){
        System.out.println("before");
    }

    @Test
    public void test1(){
        System.out.println("test1");
    }

    @Test
    public void test2(){
        System.out.println("test2");
    }

    @After
    public void after(){
        System.out.println("after");
    }
}
