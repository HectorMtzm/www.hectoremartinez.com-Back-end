package com.phoenixgb6.portfolio.universitycrm_rest.dao;

import com.phoenixgb6.portfolio.universitycrm.entity.Instructor;
import org.hibernate.tool.schema.spi.Exporter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface InstructorRepository extends JpaRepository<Instructor, Integer>{

//    @RestResource(exported=false)
//    void deleteById(int id);
}