package com.phoenixgb6.portfolio.universitycrm.dao;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentDAOImpl implements DAO<Student> {

    // Define field for entity manager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Student> findAll() {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        //create a query
        Query<Student> query = session.createQuery("from Student", Student.class);

        //execute query and get the result list
        return query.getResultList();
    }

    @Override
    public List<Student> findAll(int pageNumber, int pageSize) {

        //get all, not pages
//        //get current session
//        Session session = entityManager.unwrap(Session.class);
//
//        //create a query
//        Query<Student> query = session.createQuery("from Student", Student.class);
//
//        //execute query and get the result list
//        List<Student> studentList = query.getResultList();
//
//        return studentList;

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Student s order by  s.id", Student.class);
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

        List<Student> studentList = query.getResultList();

        return studentList;
    }

    @Override
    public Optional<Student> findById(int id) {

        Session session = entityManager.unwrap(Session.class);

        return Optional.ofNullable(session.get(Student.class, id));
    }

    @Override
    public void save(Student student) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(student);
    }

    @Override
    public void deleteById(int id) {

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Student where id=:id");
        query.setParameter("id",id);

        query.executeUpdate();
    }

    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("select count(id) from Student", Long.class);
        long count = query.getSingleResult().longValue();

        return count;
    }
}
