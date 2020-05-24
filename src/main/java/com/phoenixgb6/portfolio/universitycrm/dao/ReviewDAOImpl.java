package com.phoenixgb6.portfolio.universitycrm.dao;

import com.phoenixgb6.portfolio.universitycrm.entity.Review;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class ReviewDAOImpl implements DAO<Review>{

    // Define field for entity manager
    private EntityManager entityManager;

    //set up constructor injection
    @Autowired
    public ReviewDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Review> findAll() {
        //get current session
        Session session = entityManager.unwrap(Session.class);

        //create a query
        Query<Review> query = session.createQuery("from Review", Review.class);

        //execute query and get the result list
        return query.getResultList();
    }

    @Override
    public List<Review> findAll(int pageNumber, int pageSize, int orderBy, String name) {

        //get current session
        Session session = entityManager.unwrap(Session.class);

        String queryString = "";

        if(orderBy == 1){
            queryString = "from Review s order by  s.id";
        }
        else if(orderBy == 2){
            queryString = "from Review s order by  s.firstName";
        }
        else if(orderBy == 3){
            queryString = "from Review s order by  s.lastName";
        }

        Query query = session.createQuery(queryString, Review.class);
        query.setFirstResult((pageNumber-1) * pageSize);
        query.setMaxResults(pageSize);

        List<Review> reviewList = query.getResultList();

        return reviewList;
    }

    @Override
    public Optional<Review> findById(int id) {
        Session session = entityManager.unwrap(Session.class);

        return Optional.ofNullable(session.get(Review.class, id));
    }

    @Override
    public void save(Review review) {

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(review);
    }

    @Override
    public void deleteById(int id) {

        Session session = entityManager.unwrap(Session.class);

        session.delete(findById(id));
    }

    @Override
    public long count() {
        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery("select count(id) from Review", Long.class);
        long count = query.getSingleResult().longValue();

        return count;
    }

    @Override
    public long count(String code) {
        return 0;
    }
}
