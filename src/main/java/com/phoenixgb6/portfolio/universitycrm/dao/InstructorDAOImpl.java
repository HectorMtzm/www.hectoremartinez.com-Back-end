package com.phoenixgb6.portfolio.universitycrm.dao;

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
    public List<Instructor> findAll(int pageNumber, int pageSize, int orderBy, String name) {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        String queryString = "from Instructor s ";
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

        Query query = session.createQuery(queryString, Instructor.class);

        if(!name.isEmpty()) {
            query.setParameter("fname",names[0]);
            query.setParameter("lname",names[1]);
        }

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

//        Query query = session.createQuery("delete from Instructor where id=:id");
//        query.setParameter("id",id);
//
//        query.executeUpdate();

        Instructor instructor = session.get(Instructor.class, id);
        session.delete(instructor);
    }

    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("select count(id) from Instructor", Long.class);
        long count = query.getSingleResult().longValue();

        return count;
    }

    @Override
    public long count(String name) {
        Session session = entityManager.unwrap(Session.class);

        String[] names = name.split(" ");

        Query<Long> query = session.createQuery("select count(*) from Instructor i where i.firstName=:fname and i.lastName=:lname", Long.class);
        query.setParameter("fname", names[0]);
        query.setParameter("lname", names[1]);

        long count = query.getSingleResult().longValue();

        return count;
    }
}
