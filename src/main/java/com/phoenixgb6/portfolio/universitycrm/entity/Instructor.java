package com.phoenixgb6.portfolio.universitycrm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

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
    @JoinColumn(name="instructor_detail_id")
    private InstructorDetail details;

    @OneToMany(fetch=FetchType.LAZY,
            mappedBy="instructor",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
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

    public InstructorDetail getDetails() {
        return details;
    }

    public void setDetails(InstructorDetail instructorDetail) {
        this.details = instructorDetail;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    // Convenience methods for bi-directional relationship
    public void addCourse(Course course) {

        if (courses == null) {
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setInstructor(this);
    }

    public boolean removeCourse(Course course){

        if(courses != null){
            if(courses.remove(course)){
                course.setInstructor(null);
                return true;
            }
        }
        return false;
    }
}
