package com.phoenixgb6.portfolio.universitycrm_rest.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.CourseRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.StudentRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.AlreadyExistExceptionRest;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.NotFoundExceptionRest;
import com.phoenixgb6.portfolio.universitycrm_rest.utilityentity.idObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping("/")
public class StudentRestController {

    StudentRepository studentRepository;
    CourseRepository courseRepository;

    @Autowired
    public StudentRestController(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/students/{sid}/courses")
    public void addCourse(@PathVariable("sid") int studentId, @RequestBody idObject object){

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundExceptionRest("Student ID not found - " + studentId));
        Course course = courseRepository.findById(object.getId()).orElseThrow(() -> new NotFoundExceptionRest("Course ID not found - " + object.getId()));

        try{
            student.addCourse(course);
            studentRepository.save(student);
        } catch (DataIntegrityViolationException ex){
            throw new AlreadyExistExceptionRest("The student is already registered in course with ID " + object.getId());
        }
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/students/{sid}/courses")
    public void dropCourse(@PathVariable("sid") int studentId, @RequestBody idObject object){

        Student student = studentRepository.findById(studentId).orElseThrow(() -> new NotFoundExceptionRest("Student ID not found - " + studentId));
        Course course = courseRepository.findById(object.getId()).orElseThrow(() -> new NotFoundExceptionRest("Course ID not found - " + object.getId()));

        if(!student.dropCourse(course)){
            throw new NotFoundExceptionRest("Student with ID " + studentId + " is not enrolled in course with ID " + object.getId());
        }
        studentRepository.save(student);
    }


}
