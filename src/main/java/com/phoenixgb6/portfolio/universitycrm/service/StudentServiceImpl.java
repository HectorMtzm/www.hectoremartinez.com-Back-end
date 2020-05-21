package com.phoenixgb6.portfolio.universitycrm.service;

import com.phoenixgb6.portfolio.universitycrm.dao.DAO;
import com.phoenixgb6.portfolio.universitycrm.dao.StudentDAOImpl;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements ServiceS<Student> {

    private DAO studentDAO;

    @Autowired
    public StudentServiceImpl(StudentDAOImpl studentDAO) {
        this.studentDAO = studentDAO;
    }


    @Override
    @Transactional
    public List<Student> findAll() {
        return studentDAO.findAll();
    }

    @Override
    @Transactional
    public Student findById(int id) {
        Optional<Student> result = studentDAO.findById(id);

        Student student = null;

        if (result.isPresent()) {
            student = result.get();
        }
        else {
            // we didn't find the student
            throw new RuntimeException("Did not find student id - " + id);
        }

        return student;
    }

    @Override
    @Transactional
    public void save(Student student) {
        studentDAO.save(student);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        studentDAO.deleteById(id);
    }
}