package nokhrina.daria.attendance_web.repo;

import nokhrina.daria.attendance_web.model.relationship.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepo extends JpaRepository<StudentCourse, Long> {

}