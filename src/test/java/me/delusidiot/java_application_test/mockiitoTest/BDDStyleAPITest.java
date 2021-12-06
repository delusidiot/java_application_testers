package me.delusidiot.java_application_test.mockiitoTest;

import me.delusidiot.java_application_test.domain.Member;
import me.delusidiot.java_application_test.domain.Study;
import me.delusidiot.java_application_test.member.MemberService;
import me.delusidiot.java_application_test.study.StudyRepository;
import me.delusidiot.java_application_test.study.StudyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BDDStyleAPITest {
    // 애플리케이션이 어떻게 행동해야 하는지에 대한 공통된 이해를 구성하는 방법 (TDD에서 창안)

    /*
    Title
    Narrative
        As a/ I want /so that
    Acceptance criteria
        Given / When / Then
     */

    @Mock
    MemberService memberServiceMock;
    @Mock
    StudyRepository repositoryMock;

    @DisplayName("should....")
    @Test
    void startBDDStyle(){
        //Given
        Study study = new Study("test", 10);
        Member member = new Member();
        member.setEmail("delusidiot@gmail.com");
        member.setId(1L);

        BDDMockito.given(memberServiceMock.findById(1L)).willReturn(Optional.of(member));
        BDDMockito.given(repositoryMock.save(study)).willReturn(study);
        StudyService studyService = new StudyService(memberServiceMock, repositoryMock);

        //When
        studyService.createNewStudy(1L, study);

        //Then

        BDDMockito.then(memberServiceMock).should(times(1)).notify(study);
        BDDMockito.then(memberServiceMock).should(times(1)).notify(member);
        BDDMockito.then(memberServiceMock).shouldHaveNoInteractions();
    }
}
