package com.phoenixgb6.portfolio.universitycrm.service;

import com.phoenixgb6.portfolio.universitycrm.dao.DAO;
import com.phoenixgb6.portfolio.universitycrm.dao.InstructorDAOImpl;
import com.phoenixgb6.portfolio.universitycrm.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServiceImpl implements ServiceS<Instructor> {

    private DAO instructorDAO;

    @Autowired
    public InstructorServiceImpl(InstructorDAOImpl instructorDAO) {
        this.instructorDAO = instructorDAO;
    }


    @Override
    @Transactional
    public List<Instructor> findAll() {
        return instructorDAO.findAll();
    }

    @Override
    @Transactional
    public Instructor findById(int id) {
        Optional<Instructor> result = instructorDAO.findById(id);

        Instructor instructor = null;

        if (result.isPresent()) {
            instructor = result.get();
        }
        else {
            // we didn't find the instructor
            throw new RuntimeException("Did not find instructor id - " + id);
        }

        return instructor;
    }

    @Override
    @Transactional
    public void save(Instructor instructor) {
        instructorDAO.save(instructor);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        instructorDAO.deleteById(id);
    }
}
