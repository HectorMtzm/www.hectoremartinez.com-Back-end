package com.phoenixgb6.portfolio.universitycrm_rest.controller;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Instructor;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.CourseRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.InstructorRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.NotFoundExceptionRest;
import com.phoenixgb6.portfolio.universitycrm_rest.exception.ErrorResponse;
import com.phoenixgb6.portfolio.universitycrm_rest.utilityentity.idObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RepositoryRestController
@RequestMapping("/")
public class InstructorRestController {

    CourseRepository courseRepository;
    InstructorRepository instructorRepository;

    @Autowired
    public InstructorRestController(CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    // Replaces the delete implemented by spring data jpa.
    // Course's instructor needs to be set to null before deleting the instructor to avoid DB error.
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/instructors/{iid}")
    public void deleteInstructor(@PathVariable("iid") int instructorId){

        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NotFoundExceptionRest("Instructor ID not found - " + instructorId));

        List<Course> courseList = instructor.getCourses();

        for(Course course : courseList){
            course.setInstructor(null);
        }

        instructorRepository.save(instructor);
        instructorRepository.delete(instructor);

    }


    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/instructors/{iid}/courses")
    public void dropCourse(@PathVariable("iid") int instructorId, @RequestBody idObject object){

        Instructor instructor = instructorRepository.findById(instructorId).orElseThrow(() -> new NotFoundExceptionRest("Instructor ID not found - " + instructorId));
        Course course = courseRepository.findById(object.getId()).orElseThrow(() -> new NotFoundExceptionRest("Course ID not found - " + object.getId()));

        if(!instructor.removeCourse(course)){
            throw new NotFoundExceptionRest("Instructor with ID " + instructorId + " is not teaching course with ID " + object.getId());
        }
        instructorRepository.save(instructor);
    }
}
