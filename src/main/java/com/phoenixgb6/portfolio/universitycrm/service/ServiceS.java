package com.phoenixgb6.portfolio.universitycrm.service;

import java.util.List;

public interface ServiceS<T>{

    public List<T> findAll();

    public List<T> findAll(int pageNumber, int pageSize, int orderBy, String name);

    public T findById(int id);

    public void save (T t);

    public void deleteById(int id);

    public long count();

    public long count(String str);
}