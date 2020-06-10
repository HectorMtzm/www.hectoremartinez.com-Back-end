package com.phoenixgb6.portfolio.universitycrm_rest.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Review;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.CourseRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.ReviewRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.StudentRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.AlreadyExistExceptionRest;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.NotFoundExceptionRest;
import com.phoenixgb6.portfolio.universitycrm_rest.utilityentity.idObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RepositoryRestController
@RequestMapping("/")
public class CourseRestController {

    CourseRepository courseRepository;
    StudentRepository studentRepository;
    ReviewRepository reviewRepository;

    @Autowired
    public CourseRestController(CourseRepository courseRepository, StudentRepository studentRepository, ReviewRepository reviewRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.reviewRepository = reviewRepository;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses/{cid}/students")
    public void addStudent(@PathVariable("cid") int courseId, @RequestBody idObject object){

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundExceptionRest("Course ID not found - " + courseId));
        Student student = studentRepository.findById(object.getId()).orElseThrow(() -> new NotFoundExceptionRest("Student ID not found - " + object.getId()));

        try{
            course.addStudent(student);
            courseRepository.save(course);
        } catch (DataIntegrityViolationException ex){
            throw new AlreadyExistExceptionRest("The student with ID " + object.getId() + " is already registered in course with ID " + object.getId());
        }
    }

//    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/courses/{cid}/students")
    public void dropCourse(@PathVariable("cid") int courseId, @RequestBody idObject object){

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundExceptionRest("Course ID not found - " + courseId));
        Student student = studentRepository.findById(object.getId()).orElseThrow(() -> new NotFoundExceptionRest("Student ID not found - " + object.getId()));

        if(!course.removeStudent(student)){
            throw new NotFoundExceptionRest("Student with ID " + object.getId() + " is not enrolled in course with ID " + courseId);
        }
        studentRepository.save(student);
    }

    //override the method to not authorize updates in the StudentList of the course.
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PutMapping("/courses/{cid}/students")
    public void updateStudentList(@PathVariable("cid") int courseId){
    }




    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/courses/{cid}/reviews")
    public void addReview(@PathVariable("cid") int courseId, @RequestBody Review review) throws Exception {

        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundExceptionRest("Course ID not found - " + courseId));

        try{
            course.addReview(review);
            courseRepository.save(course);
        } catch (Exception ex){
            throw new Exception("Bad request");
        }
    }

    //    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @DeleteMapping("/courses/{cid}/reviews")
    public void removeReview(@PathVariable("cid") int courseId){
    }

    //override the method to not authorize updates in the reviews of the course.
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PutMapping("/courses/{cid}/reviews")
    public void updateReviewList(@PathVariable("cid") int courseId){
    }
}
