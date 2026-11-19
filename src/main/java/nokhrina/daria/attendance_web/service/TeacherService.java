package nokhrina.daria.attendance_web.service;

import nokhrina.daria.attendance_web.model.Teacher;
import nokhrina.daria.attendance_web.repo.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public void addTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    public Teacher getByID(Long teacherID) {
        return teacherRepository.getReferenceById(teacherID);
    }
}
