package nokhrina.daria.attendance_web.controller;

import nokhrina.daria.attendance_web.model.Student;
import nokhrina.daria.attendance_web.repo.CourseRepository;
import nokhrina.daria.attendance_web.repo.relationships.StudentCourseRepository;
import nokhrina.daria.attendance_web.repo.StudentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class StudentController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseRepository studentCourseRepository;

    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository, StudentCourseRepository studentCourseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    @GetMapping("/students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "students";
    }



    @GetMapping("/students/add")
    public String createCourseForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/students/add")
    public String addCourse(@ModelAttribute("student") Student student) {
        studentRepository.save(student);
        return "redirect:/courses";
    }
}
