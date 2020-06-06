package com.phoenixgb6.portfolio.universitycrm.entity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "{firstName.blank}")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "{lastName.blank}")
    @Column(name = "last_name")
    private String lastName;

    @NotBlank(message = "{email.blank}")
    @Email(message = "{email.invalid}")
    @Column(name = "email")
    private String email;

    @Valid
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="student_detail_id")
    private StudentDetail details;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="student_id"),
            inverseJoinColumns=@JoinColumn(name="course_id")
    )
    private List<Course> courses;

    public Student() {
    }

    public Student(String firstName, String lastName, String email, StudentDetail details) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StudentDetail getDetails() {
        return details;
    }

    public void setDetails(StudentDetail studentDetail) {
        this.details = studentDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void dropCourse(Course tempCourse){

        if(courses != null){
            courses.remove(tempCourse);
        }
    }

    public void addCourse(Course course){
        courses.add(course);
    }
}
