package com.practice.spring.repository;

import com.practice.spring.dao.UserDAO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// @RepositoryRestResource can be used to directly expose end points for this repository
@Repository
public interface UserRepository extends CrudRepository<UserDAO, String>{

}
