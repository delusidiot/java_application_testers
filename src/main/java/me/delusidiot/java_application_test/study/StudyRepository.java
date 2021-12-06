package me.delusidiot.java_application_test.study;

import me.delusidiot.java_application_test.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<Study, Long> {

}
