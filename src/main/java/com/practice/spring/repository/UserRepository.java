package com.practice.spring.repository;

import com.practice.spring.dao.UserDAO;
import org.springframework.data.repository.CrudRepository;

// @RepositoryRestResource can be used to directly expose end points for this repository
public interface UserRepository extends CrudRepository<UserDAO, String>{

}
