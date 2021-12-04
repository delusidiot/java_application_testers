package me.delusidiot.java_application_test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {

    @Test
    @DisplayName("스터디 만들기")
    void create_study_shouldSuccess(){
        Study study = new Study();
        assertNotNull(study);
        assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 " + StudyStatus.DRAFT + "여야 한다.");
    }

    @Test
    @DisplayName("스터디 인원 제한")
    void studyLimit_shouldTrue() throws IllegalAccessException {
        Study study = new Study(10);
        assertTrue(study.getLimit() > 0);
    }

    @Test
    @DisplayName("스터디 만들고 인원제한 확인하기")
    void create_study_and_check_limit_shouldSuccess() throws IllegalAccessException {
        Study study = new Study(10);
        assertAll(
                () -> assertNotNull(study),
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus()),
                () -> assertTrue(study.getLimit() > 0)
        );
    }

    @Test
    @DisplayName("스터디 인원제한 설절")
    void check_study_shouldFail() {
        Exception exception = assertThrows(IllegalAccessException.class, () -> new Study(-1));
        assertEquals(exception.getMessage(), "limit은 0보다 커야 한다.");
    }

    @Test
    @DisplayName("실행 시간 확인")
    void check_execute_time_shouldSuccess(){
        assertTimeout(Duration.ofSeconds(10), () -> new Study(10));
    }
}