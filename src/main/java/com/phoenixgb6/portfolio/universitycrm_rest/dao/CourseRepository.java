package com.phoenixgb6.portfolio.universitycrm_rest.dao;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
