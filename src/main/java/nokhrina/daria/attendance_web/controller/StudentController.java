package nokhrina.daria.attendance_web.controller;

import nokhrina.daria.attendance_web.model.Course;
import nokhrina.daria.attendance_web.model.Student;
import nokhrina.daria.attendance_web.model.StudentCourse;
import nokhrina.daria.attendance_web.repo.CourseRepo;
import nokhrina.daria.attendance_web.repo.StudentCourseRepo;
import nokhrina.daria.attendance_web.repo.StudentRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class StudentController {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final StudentCourseRepo studentCourseRepo;

    public StudentController(StudentRepo studentRepository, CourseRepo courseRepo, StudentCourseRepo studentCourseRepo) {
        this.studentRepo = studentRepository;
        this.courseRepo = courseRepo;
        this.studentCourseRepo = studentCourseRepo;
    }

    @GetMapping("/students")
    public String getStudents(Model model) {
        model.addAttribute("students", studentRepo.findAll());
        return "students";
    }

    @GetMapping("/students/add")
    public String createCourseForm(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @PostMapping("/students/add")
    public String addCourse(@ModelAttribute("student") Student student) {
        studentRepo.save(student);

        return "redirect:/courses";
    }
}
