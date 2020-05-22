package com.phoenixgb6.portfolio.universitycrm.dao;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import com.phoenixgb6.portfolio.universitycrm.entity.Instructor;
import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class InstructorDAOImpl implements DAO<Instructor>{

    // Define field for entity manager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public InstructorDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Instructor> findAll() {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        //create a query
        Query<Instructor> query = session.createQuery("from Instructor", Instructor.class);

        //execute query and get the result list
        return query.getResultList();
    }

    @Override
    public List<Instructor> findAll(int pageNumber, int pageSize) {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("from Instructor s order by  s.id", Instructor.class);
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

        List<Instructor> instructorList = query.getResultList();

        return instructorList;
    }

    @Override
    public Optional<Instructor> findById(int id) {
        Session session = entityManager.unwrap(Session.class);

        return Optional.ofNullable(session.get(Instructor.class, id));
    }

    @Override
    public void save(Instructor instructor) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(instructor);
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery("delete from Instructor where id=:id");
        query.setParameter("id",id);

        query.executeUpdate();
    }

    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("select count(id) from Instructor", Long.class);
        long count = query.getSingleResult().longValue();

        return count;
    }
}
