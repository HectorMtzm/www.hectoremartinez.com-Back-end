package com.phoenixgb6.portfolio.universitycrm_rest.controller;

import com.phoenixgb6.portfolio.universitycrm_rest.dao.CourseRepository;
import com.phoenixgb6.portfolio.universitycrm_rest.dao.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RepositoryRestController
@RequestMapping("/")
public class ReviewRestController {

    ReviewRepository reviewRepository;
    CourseRepository courseRepository;

    @Autowired
    public ReviewRestController(ReviewRepository reviewRepository, CourseRepository courseRepository) {
        this.reviewRepository = reviewRepository;
        this.courseRepository = courseRepository;
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PostMapping("/reviews")
    public void addReview(){
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @PutMapping("/reviews")
    public void updateReview(){
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @DeleteMapping("/reviews")
    public void deleteReview(){
    }
}
