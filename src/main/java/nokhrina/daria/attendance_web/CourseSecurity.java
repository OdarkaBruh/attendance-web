package nokhrina.daria.attendance_web;

import nokhrina.daria.attendance_web.enums.web.Privileges;
import nokhrina.daria.attendance_web.repo.CourseRepository;
import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

@Component("courseSecurity")
public class CourseSecurity {

    private final CourseRepository courseRepository;

    public CourseSecurity(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public boolean canEdit(Authentication auth, Long courseId) {
        if (hasAuthority(auth, Privileges.EDIT_ALL_COURSES)) {
            return true;
        }
        if (!hasAuthority(auth, Privileges.EDIT_OWNED_COURSES)) {
            return false;
        }
        Long userId = ((CustomUserDetails) auth.getPrincipal()).getId();
        return courseRepository.findById(courseId)
                .map(course -> course.getTeacher().getId().equals(userId))
                .orElse(false);
    }

    private boolean hasAuthority(Authentication auth, Privileges privilege) {
        return auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals(privilege.name()));
    }
}