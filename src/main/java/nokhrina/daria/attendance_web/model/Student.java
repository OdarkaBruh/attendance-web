package nokhrina.daria.attendance_web.model;

import jakarta.persistence.*;
import nokhrina.daria.attendance_web.model.relationship.StudentCourse;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends Person {

    @OneToMany(mappedBy = "student")
    private List<StudentCourse> courses = new ArrayList<>();

    public Student() {
    }

    public List<StudentCourse> getCourses() {
        return courses;
    }
}