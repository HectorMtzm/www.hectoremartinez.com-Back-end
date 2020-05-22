package com.phoenixgb6.portfolio.universitycrm.service;

import com.phoenixgb6.portfolio.universitycrm.dao.DAO;
import com.phoenixgb6.portfolio.universitycrm.dao.ReviewDAOImpl;
import com.phoenixgb6.portfolio.universitycrm.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ServiceS<Review>{
    private DAO reviewDAO;

    @Autowired
    public ReviewServiceImpl(ReviewDAOImpl reviewDAO) {
        this.reviewDAO = reviewDAO;
    }


    @Override
    public List<Review> findAll() {
        return reviewDAO.findAll();
    }

    @Override
    @Transactional
    public List<Review> findAll(int pageNumber, int pageSize) {
        return reviewDAO.findAll(pageNumber, pageSize);
    }

    @Override
    @Transactional
    public Review findById(int id) {
        Optional<Review> result = reviewDAO.findById(id);

        Review review = null;

        if (result.isPresent()) {
            review = result.get();
        }
        else {
            // we didn't find the review
            throw new RuntimeException("Did not find review id - " + id);
        }

        return review;
    }

    @Override
    @Transactional
    public void save(Review review) {
        reviewDAO.save(review);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        reviewDAO.deleteById(id);
    }

    @Override
    @Transactional
    public long count(){
        return reviewDAO.count();
    }
}
