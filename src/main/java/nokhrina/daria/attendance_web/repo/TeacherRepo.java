package nokhrina.daria.attendance_web.repo;

import nokhrina.daria.attendance_web.model.Student;
import nokhrina.daria.attendance_web.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepo extends JpaRepository<Teacher, Long> {

}
