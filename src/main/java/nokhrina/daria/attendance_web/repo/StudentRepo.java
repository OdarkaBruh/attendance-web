package nokhrina.daria.attendance_web.repo;

import nokhrina.daria.attendance_web.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

}
