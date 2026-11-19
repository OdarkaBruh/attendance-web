package nokhrina.daria.attendance_web.service;

import nokhrina.daria.attendance_web.model.Course;
import nokhrina.daria.attendance_web.repo.CourseRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    
    public CourseService(CourseRepository courseRepository) {this.courseRepository = courseRepository;}

    public Optional<Course> getCourseById(Long id){
        return courseRepository.findById(id);
    }

    public @Nullable Object findAll() {
        return courseRepository.findAll();
    }

    public void addCourse(Course course) {

        courseRepository.save(course);
    }
}
