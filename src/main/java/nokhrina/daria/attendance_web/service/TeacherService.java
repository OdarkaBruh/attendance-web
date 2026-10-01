package nokhrina.daria.attendance_web.service;

import nokhrina.daria.attendance_web.model.Course;
import nokhrina.daria.attendance_web.model.Teacher;
import nokhrina.daria.attendance_web.repo.TeacherRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;

    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepo.findAll();
    }

    public void addTeacher(Teacher teacher) {
        teacherRepo.save(teacher);
    }

    public Teacher getByID(Long teacherID) {
        return teacherRepo.getReferenceById(teacherID);
    }
}
