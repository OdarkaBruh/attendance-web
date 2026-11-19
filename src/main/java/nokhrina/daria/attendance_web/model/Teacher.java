package nokhrina.daria.attendance_web.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher extends Person {

    private String position;

    public Teacher() {
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}