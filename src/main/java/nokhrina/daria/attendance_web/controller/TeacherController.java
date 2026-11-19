package nokhrina.daria.attendance_web.controller;

import nokhrina.daria.attendance_web.model.Teacher;
import nokhrina.daria.attendance_web.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    private final TeacherService service;

    public TeacherController (TeacherService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public String getTeachers(Model model) {
        model.addAttribute("teachers", service.getAllTeachers());
        return "teachers/teachers";
    }

    @GetMapping("/add")
    public String addTeacher(Model model) {
        model.addAttribute("teacher", new Teacher());
        return "teachers/create-teacher";
    }

    @PostMapping("/add")
    public String addTeacher (@ModelAttribute("teacher") Teacher teacher) {
        service.addTeacher(teacher);
        return "redirect:/teachers/all";
    }
}
