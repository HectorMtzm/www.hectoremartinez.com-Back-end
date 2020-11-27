package com.phoenixgb6.portfolio.universitycrm.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotBlank(message = "{firstName.blank}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{firstName.invalid}")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "{lastName.blank}")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "{lastName.invalid}")
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

    public boolean dropCourse(Course course){

        if(courses != null){
            if(courses.remove(course)){
                return true;
            }
        }
        return false;
    }

    public void addCourse(Course course){
        if(courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }
}
