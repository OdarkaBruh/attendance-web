package nokhrina.daria.attendance_web.repo;

import nokhrina.daria.attendance_web.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}