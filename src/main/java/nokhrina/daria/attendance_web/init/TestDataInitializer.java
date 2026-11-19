package nokhrina.daria.attendance_web.init;

import jakarta.transaction.Transactional;
import nokhrina.daria.attendance_web.manager.RoleManager;
import nokhrina.daria.attendance_web.model.Course;
import nokhrina.daria.attendance_web.model.Student;
import nokhrina.daria.attendance_web.model.Teacher;
import nokhrina.daria.attendance_web.model.web.Role;
import nokhrina.daria.attendance_web.model.web.UserProfile;
import nokhrina.daria.attendance_web.repo.CourseRepository;
import nokhrina.daria.attendance_web.repo.StudentRepository;
import nokhrina.daria.attendance_web.repo.TeacherRepository;
import nokhrina.daria.attendance_web.repo.web.UserRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Order(2)
public class TestDataInitializer implements ApplicationRunner {

    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final RoleManager roleManager;
    private final PasswordEncoder passwordEncoder;

    public TestDataInitializer(UserRepository userRepository,
                               TeacherRepository teacherRepository,
                               StudentRepository studentRepository,
                               CourseRepository courseRepository,
                               RoleManager roleManager,
                               PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.roleManager = roleManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        if (userRepository.count() > 0) {
            return; // already seeded
        }

        // Accounts without a profile
        createUser("admin", "admin@test.com", roleManager.getAdminRole());
        createUser("user1", "user1@test.com", roleManager.getUserRole());

        // Teachers
        Teacher teacher1 = createTeacher("teacher1", "teacher1@test.com", "Anna", "Petrenko", "Senior Lecturer");
        Teacher teacher2 = createTeacher("teacher2", "teacher2@test.com", "Ivan", "Kovalenko", "Professor");

        // Students
        createStudent("student1", "student1@test.com", "Olena", "Shevchenko");
        createStudent("student2", "student2@test.com", "Maksym", "Bondar");

        // Courses
        courseRepository.saveAll(List.of(
                createCourse("Java Basics", "Introduction to Java", teacher1),
                createCourse("Spring Boot", "Building web applications", teacher1),
                createCourse("Databases", "SQL and relational design", teacher2),
                createCourse("Algorithms", "Core algorithms and data structures", teacher2)
        ));
    }

    private UserProfile createUser(String username, String email, Role role) {
        UserProfile user = new UserProfile(
                username,
                passwordEncoder.encode("password"),
                email,
                new ArrayList<>(List.of(role)));
        return userRepository.save(user);
    }

    private Teacher createTeacher(String username, String email,
                                  String firstName, String lastName, String position) {
        UserProfile account = createUser(username, email, roleManager.getTeacherRole());
        Teacher teacher = new Teacher();
        teacher.setFirstName(firstName);
        teacher.setLastName(lastName);
        teacher.setPosition(position);
        teacher.setUserAccount(account);
        return teacherRepository.save(teacher);
    }

    private Student createStudent(String username, String email, String firstName, String lastName) {
        UserProfile account = createUser(username, email, roleManager.getStudentRole());
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setUserAccount(account);
        return studentRepository.save(student);
    }

    private Course createCourse(String name, String description, Teacher teacher) {
        Course course = new Course();
        course.setName(name);
        course.setDescription(description);
        course.setTeacher(teacher);
        return course;
    }
}