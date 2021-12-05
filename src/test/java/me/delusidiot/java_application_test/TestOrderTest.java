package me.delusidiot.java_application_test;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestOrderTest {
    // 테스트 순서는 보장하지 않습니다.
    // 기본적으로 하나의 단위 테스트는 다른테스트와 독립적으로 실행되어야합니다. 즉 서로간의 의존성이 없어야 합니다. 그렇기 때문에 순서를 보장하지 않습니다.
    // 경우에 따라서 (시나리오 테스트의경우 회원가입->로그인) 순서를 보장하고 테스트간의 의존성이 있어야하는 경우가 있습니다.
    // 테스트 순서만 할수 있긴하지만 같은 인스턴스를 사용해야하는 경우가 아니라면 굳이 순서를 정해주지 않는것이 테스트에 목적을 더 명확하게 할 수 있을 것 같습니다.
    Study study;
    @Order(1)
    @Test
    @DisplayName("Study 생성")
    void create_study(){
        study = new Study();
    }

    @Order(2)
    @DisplayName("Study 이용")
    @Test
    void use_study(){
        System.out.println(study.getLimit());
    }

}
