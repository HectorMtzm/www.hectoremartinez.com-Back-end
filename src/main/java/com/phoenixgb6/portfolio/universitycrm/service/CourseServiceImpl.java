package com.phoenixgb6.portfolio.universitycrm.service;

import com.phoenixgb6.portfolio.universitycrm.dao.DAO;
import com.phoenixgb6.portfolio.universitycrm.dao.CourseDAOImpl;
import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ServiceS<Course> {
    
    private DAO courseDAO;

    @Autowired
    public CourseServiceImpl(CourseDAOImpl courseDAO) {
        this.courseDAO = courseDAO;
    }


    @Override
    public List<Course> findAll() {
        return courseDAO.findAll();
    }

    @Override
    @Transactional
    public List<Course> findAll(int pageNumber, int pageSize) {
        return courseDAO.findAll(pageNumber, pageSize);
    }

    @Override
    @Transactional
    public Course findById(int id) {
        Optional<Course> result = courseDAO.findById(id);

        Course course = null;

        if (result.isPresent()) {
            course = result.get();
        }
        else {
            // we didn't find the course
            throw new RuntimeException("Did not find course id - " + id);
        }

        return course;
    }

    @Override
    @Transactional
    public void save(Course course) {
        courseDAO.save(course);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        courseDAO.deleteById(id);
    }

    @Override
    @Transactional
    public long count(){
        return courseDAO.count();
    }
}
