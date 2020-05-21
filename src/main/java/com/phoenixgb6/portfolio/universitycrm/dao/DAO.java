package com.phoenixgb6.portfolio.universitycrm.dao;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    public List<T> findAll();

    public Optional<T> findById(int id);

    public void save (T t);

    public void deleteById(int id);
}
