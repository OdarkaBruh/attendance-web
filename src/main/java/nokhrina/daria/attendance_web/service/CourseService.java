package nokhrina.daria.attendance_web.service;

import nokhrina.daria.attendance_web.model.Course;
import nokhrina.daria.attendance_web.repo.CourseRepo;
import nokhrina.daria.attendance_web.repo.TeacherRepo;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepo courseRepo;
    
    public CourseService(CourseRepo courseRepo) {this.courseRepo = courseRepo;}

    public Optional<Course> getCourseById(Long id){
        return courseRepo.findById(id);
    }

    public @Nullable Object findAll() {
        return courseRepo.findAll();
    }

    public void addCourse(Course course) {

        courseRepo.save(course);
    }
}
