package me.delusidiot.java_application_test.member;

import me.delusidiot.java_application_test.domain.Member;
import me.delusidiot.java_application_test.domain.Study;

import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newStudy);

    void notify(Member member);
}
