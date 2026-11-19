package nokhrina.daria.attendance_web.model.relationship;

import jakarta.persistence.*;
import nokhrina.daria.attendance_web.enums.EnrollmentStatus;
import nokhrina.daria.attendance_web.model.Course;
import nokhrina.daria.attendance_web.model.Student;

import java.time.LocalDate;

@Entity
@Table(
        name = "students_courses",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"student_id", "course_id"}
                )
        }
)
public class StudentCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = EnrollmentStatus.ACTIVE;

    public StudentCourse() {
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}
