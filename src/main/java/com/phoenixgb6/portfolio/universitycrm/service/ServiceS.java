package com.phoenixgb6.portfolio.universitycrm.service;

import java.util.List;
import java.util.Optional;

public interface ServiceS<T>{

    public List<T> findAll();

    public T findById(int id);

    public void save (T t);

    public void deleteById(int id);
}