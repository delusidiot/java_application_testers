package me.delusidiot.java_application_test.study;

import me.delusidiot.java_application_test.domain.Member;
import me.delusidiot.java_application_test.domain.Study;
import me.delusidiot.java_application_test.member.MemberService;

import java.util.Optional;

public class StudyService {
    private final MemberService memberService;

    private final StudyRepository studyRepository;

    public StudyService(MemberService memberService, StudyRepository studyRepository) {
        assert memberService != null;
        assert studyRepository != null;
        this.memberService = memberService;
        this.studyRepository = studyRepository;
    }

    public Study createNewStudy(Long memberId, Study study){
        Optional<Member> member = memberService.findById(memberId);
        study.setOwner(member.orElseThrow(() -> new IllegalArgumentException("Member doesn't exist for id:" + memberId)));

        Study newStudy = studyRepository.save(study);
        memberService.notify(newStudy);
        memberService.notify(member.get());
        return newStudy;
    }
}
