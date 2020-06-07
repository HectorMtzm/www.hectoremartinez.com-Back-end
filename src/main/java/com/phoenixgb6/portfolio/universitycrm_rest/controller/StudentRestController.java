package com.phoenixgb6.portfolio.universitycrm_rest.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.CourseRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.StudentRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.utilityentity.idObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RepositoryRestController
public class StudentRestController {

    StudentRepository studentRepository;
    CourseRepository courseRepository;

    @Autowired
    public StudentRestController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/portfolio/universitycrm/api/students/{sid}/courses")
    public Student addCourse(@PathVariable("sid") int studentId, @RequestBody idObject object){
        Optional<Student> opStudent = studentRepository.findById(studentId);

        Student student = opStudent.get();

        List<Course> courses = student.getCourses();

        if(courses == null){
            courses = new ArrayList<>();
        }

        Optional<Course> course = courseRepository.findById(object.getId());

        student.addCourse(course.get());

        studentRepository.save(student);

        return student;

    }
}
