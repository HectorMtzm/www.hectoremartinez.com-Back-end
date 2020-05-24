package com.phoenixgb6.portfolio.universitycrm.dao;

import com.phoenixgb6.portfolio.universitycrm.entity.Student;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
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
    public List<Student> findAll(int pageNumber, int pageSize, int orderBy, String name) {

        Session session = entityManager.unwrap(Session.class);

        String queryString = "from Student s ";
        String queryStringAlt1 = "where s.firstName=:fname and s.lastName=:lname ";
        String queryStringAlt2 = "order by ";

        String[] names = new String[2];

        if(!name.isEmpty()) {
            names = name.split(" ");
            queryString += queryStringAlt1 + queryStringAlt2;
        }
        else {
            queryString += queryStringAlt2;
        }

        if(orderBy == 1){
            queryString +=  "s.id";
        }
        else if(orderBy == 2){
            queryString += "s.firstName";
        }
        else if(orderBy == 3){
            queryString += "s.lastName";
        }

        Query query = session.createQuery(queryString, Student.class);

        if(!name.isEmpty()) {
            query.setParameter("fname",names[0]);
            query.setParameter("lname",names[1]);
        }

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

//        Query query = session.createQuery("delete from Student where id=:id");
//        query.setParameter("id",id);
//
//        query.executeUpdate();

        Student student = session.get(Student.class, id);
        session.delete(student);
    }

    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("select count(id) from Student", Long.class);
        long count = query.getSingleResult().longValue();

        return count;
    }

    @Override
    public long count(String name) {
        Session session = entityManager.unwrap(Session.class);

        String[] names = name.split(" ");

        Query<Long> query = session.createQuery("select count(*) from Student s where s.firstName=:fname and s.lastName=:lname", Long.class);
        query.setParameter("fname", names[0]);
        query.setParameter("lname", names[1]);

        long count = query.getSingleResult().longValue();

        return count;
    }
}
