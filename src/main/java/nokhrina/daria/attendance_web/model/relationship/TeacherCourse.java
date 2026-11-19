package nokhrina.daria.attendance_web.model.relationship;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import nokhrina.daria.attendance_web.model.Course;
import nokhrina.daria.attendance_web.model.Teacher;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "teachers_courses",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"teacher_id", "course_id"}
                )
        }
)
public class TeacherCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    public TeacherCourse() {
    }

    public Long getId() {
        return id;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}