package com.phoenixgb6.portfolio.universitycrm.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="title")
    private String title;

    @Column(name="prefix")
    private String prefix;

    @Column(name ="description")
    private String description;

    @Column(name = "number")
    private int number;

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

    @OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name="course_id")
    private List<Review> reviews;

    @ManyToMany(fetch=FetchType.LAZY,
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name="course_student",
            joinColumns=@JoinColumn(name="course_id"),
            inverseJoinColumns=@JoinColumn(name="student_id")
    )
    private List<Student> students;

    public Course() {
    }

    public Course(String title, String prefix, String description, int number) {
        this.title = title;
        this.prefix = prefix;
        this.description = description;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String code) {
        this.prefix = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    // convenience method
    public void addStudent(Student theStudent) {

        if (students == null) {
            students = new ArrayList<>();
        }

        students.add(theStudent);
    }

    // Convenience method
    public void addReview(Review theReview) {

        if (reviews == null) {
            reviews = new ArrayList<>();
        }

        reviews.add(theReview);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", prefix='" + prefix + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", instructor=" + instructor +
                ", reviews=" + reviews +
                ", students=" + students +
                '}';
    }
}
