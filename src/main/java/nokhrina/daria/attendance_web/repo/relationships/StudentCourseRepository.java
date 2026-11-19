package nokhrina.daria.attendance_web.repo.relationships;

import nokhrina.daria.attendance_web.model.relationship.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {

}