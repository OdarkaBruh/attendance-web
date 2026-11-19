package nokhrina.daria.attendance_web.controller;

import nokhrina.daria.attendance_web.model.Course;
import nokhrina.daria.attendance_web.model.Student;
import nokhrina.daria.attendance_web.model.Teacher;
import nokhrina.daria.attendance_web.service.CourseService;
import nokhrina.daria.attendance_web.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService service;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/all")
    public String getStudents(Model model) {
        model.addAttribute("courses", service.findAll());
        return "courses/courses";
    }

    @GetMapping("/add")
    public String addCourse(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("teachers", teacherService.getAllTeachers());
        return "courses/create-course";
    }

    @GetMapping("/{id}")
    public String getStudents(Model model, @PathVariable Long id) {
        Course course = service.getCourseById(id).orElseThrow();
        model.addAttribute("course", course);
        return "courses/course";
    }
    
    @PostMapping("/add")
    public String addCourse (@ModelAttribute("course") Course course) {

        service.addCourse(course);
        return "redirect:/courses/all";
    }
}
