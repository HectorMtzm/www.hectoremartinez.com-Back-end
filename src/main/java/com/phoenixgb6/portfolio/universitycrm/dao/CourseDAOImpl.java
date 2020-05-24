package com.phoenixgb6.portfolio.universitycrm.dao;

import com.phoenixgb6.portfolio.universitycrm.entity.Course;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseDAOImpl implements DAO<Course> {

    // Define field for entity manager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public CourseDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Course> findAll() {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        //create a query
        Query<Course> query = session.createQuery("from Course", Course.class);

        //execute query and get the result list
        return query.getResultList();
    }

    @Override
    public List<Course> findAll(int pageNumber, int pageSize, int orderBy, String name) {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        String queryString = "id";

        if(orderBy == 1){ //id
            queryString = "from Course s order by  s.id";
        }
        else if(orderBy == 2){ //firstname
            queryString = "from Course s order by  s.firstName";
        }
        else if(orderBy == 3){ //lastname
            queryString = "from Course s order by  s.lastName";
        }

        Query query = session.createQuery(queryString, Course.class);
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

        List<Course> courseList = query.getResultList();

        return courseList;
    }

    @Override
    public Optional<Course> findById(int id) {
        Session session = entityManager.unwrap(Session.class);

        return Optional.ofNullable(session.get(Course.class, id));
    }

    @Override
    public void save(Course course) {
        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(course);
    }

    @Override
    public void deleteById(int id) {
        Session session = entityManager.unwrap(Session.class);

//        Query query = session.createQuery("delete from Course where id=:id");
//        query.setParameter("id",id);
//
//        query.executeUpdate();

        Course course = session.get(Course.class, id);
        session.delete(course);
    }

    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("select count(id) from Course", Long.class);
        long count = query.getSingleResult().longValue();

        return count;
    }

    @Override
    public long count(String code) {
        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("select count(*) from Course c where c.code=:code", Long.class);
        query.setParameter("code", code);

        long count = query.getSingleResult().longValue();

        return count;
    }
}
