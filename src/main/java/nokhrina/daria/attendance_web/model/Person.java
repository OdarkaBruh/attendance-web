package nokhrina.daria.attendance_web.model;

import jakarta.persistence.*;
import nokhrina.daria.attendance_web.model.web.UserProfile;
@MappedSuperclass
public abstract class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private UserProfile userProfileAccount;

    public Person() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public UserProfile getUserAccount() {
        return userProfileAccount;
    }

    public void setUserAccount(UserProfile userProfileAccount) {
        this.userProfileAccount = userProfileAccount;
    }
}