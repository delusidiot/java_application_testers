package me.delusidiot.java_application_test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
public class TaggingAndFilteringTest {
    @Test
    @DisplayName("tag fast")
    @Tag("fast")
    void tag_fast() {

    }

    @Test
    @DisplayName("tag slow")
    @Tag("slow") // CI환경에서
    void tag_slow() {

    }
}
