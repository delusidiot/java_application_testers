package me.delusidiot.java_application_test.mockiitoTest;

import me.delusidiot.java_application_test.domain.Member;
import me.delusidiot.java_application_test.domain.Study;
import me.delusidiot.java_application_test.member.MemberService;
import me.delusidiot.java_application_test.study.StudyRepository;
import me.delusidiot.java_application_test.study.StudyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudyServiceTest {

    // MemberService와 StudyRepository의 구현체가 없습니다. 하지만 StudyService에 대한 테스트를 진행해야합니다.
    // 이러한 경우 Mocking을 합니다.
    @Mock
    MemberService memberServiceMock;

    @Mock
    StudyRepository repositoryMock;

    @Test
    void createStudyServiceForMockMethod() {
        MemberService memberService = mock(MemberService.class);
        StudyRepository repository = mock(StudyRepository.class);
        StudyService studyService = new StudyService(memberService, repository);
        assertNotNull(studyService);
    }

    //Mock Annotation을 사용한다고 바로 Mock이 생성되진 않습니다. ExtendWith로 Extension을 받아야 만들 수 있습니다.
    //Mock 객체를 해당 테스트클래스의 모든 곳에서 사용한다면 좋은 방법입니다.
    @Test
    void createStudyServiceForAnnotation() {
        StudyService studyService = new StudyService(memberServiceMock, repositoryMock);
        assertNotNull(studyService);
    }

    //Method 파라미터 안에서도 Mock을 생성할 수 있습니다. 위와 마찬가지로 ExtendWith로 Extension을 받아야 만들 수 있습니다.
    @Test
    void createStudyServiceForMethodParam(@Mock MemberService memberServiceMockInParam,
                                          @Mock StudyRepository repositoryMockInParam){
        StudyService studyService = new StudyService(memberServiceMockInParam, repositoryMockInParam);
        assertNotNull(studyService);
    }

    /*
    Mock 객체의 행동
    - Null을 리턴한다. (Optional 타입은 Optional.empty 리턴)
    - Primitive 타입은 기본 Primitive 값
    - 콜랙션은 비어있는 콜랙션
    - void 메소드는 예외를 던지지 않고 아무런 일도 발생하지 않는다.
     */
    @Test
    void createStudyStubbing() {
        StudyService studyService = new StudyService(memberServiceMock, repositoryMock);
        assertNotNull(studyService);

        Member member = new Member();
        member.setId(1L);
        member.setEmail("delusidiot@gmail.com");

        when(memberServiceMock.findById(1L)).thenReturn(Optional.of(member)); //1L
//        when(memberServiceMock.findById(any())).thenReturn(Optional.of(member)); //any

        Study study = new Study("java", 10);
        studyService.createNewStudy(1L, study);

        // return 값이 없는 경우
        doThrow(new IllegalArgumentException())
                .when(memberServiceMock).validate(1L);
        // validate 에 1L을 넘겨주면 예외를 줌

        assertThrows(IllegalArgumentException.class, () -> {
            memberServiceMock.validate(1L);
        });
    }

    @Test
    void createStubbingOrder() {
        Member member = new Member();
        member.setId(1L);
        member.setEmail("delusidiot@gmail.com");
        when(memberServiceMock.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Optional<Member> byId = memberServiceMock.findById(1L);
        assertEquals("delusidiot@gmail.com", byId.get().getEmail());

        assertThrows(RuntimeException.class, () -> {
            memberServiceMock.findById(2L);
        });

        assertEquals(Optional.empty(), memberServiceMock.findById(3L));
    }

    @Test
    void practice(){
        Study study = new Study("test", 10);
        Member member = new Member();
        member.setEmail("delusidiot@gmail.com");
        member.setId(1L);

        when(memberServiceMock.findById(1L)).thenReturn(Optional.of(member));
        when(repositoryMock.save(study)).thenReturn(study);
        StudyService studyService = new StudyService(memberServiceMock, repositoryMock);
        studyService.createNewStudy(1L, study);

        assertNotNull(study.getOwner());
        assertEquals(member, study.getOwner());
    }

    @Test
    //Mock객체에서 실행되는 메소드를 확인하는 방법
    //순서 까지 확인할 수 있다.
    void verifyTest(){
        Study study = new Study("test", 10);
        Member member = new Member();
        member.setEmail("delusidiot@gmail.com");
        member.setId(1L);

        when(memberServiceMock.findById(1L)).thenReturn(Optional.of(member));
        when(repositoryMock.save(study)).thenReturn(study);
        StudyService studyService = new StudyService(memberServiceMock, repositoryMock);
        studyService.createNewStudy(1L, study);

        verify(memberServiceMock, times(1)).notify(study);
        verify(memberServiceMock, times(1)).notify(member);
        //verify(memberServiceMock, never()).validate(any());
    }

    @Test
    void verifyOrderTest(){
        Study study = new Study("test", 10);
        Member member = new Member();
        member.setEmail("delusidiot@gmail.com");
        member.setId(1L);

        when(memberServiceMock.findById(1L)).thenReturn(Optional.of(member));
        when(repositoryMock.save(study)).thenReturn(study);
        StudyService studyService = new StudyService(memberServiceMock, repositoryMock);
        studyService.createNewStudy(1L, study);
        InOrder inOrder = inOrder(memberServiceMock);
        inOrder.verify(memberServiceMock).notify(study);
        inOrder.verify(memberServiceMock).notify(member);
        verifyNoMoreInteractions(memberServiceMock);
    }
}
