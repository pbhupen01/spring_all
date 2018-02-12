package com.practice.spring.repository;

import com.practice.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Cacheable;

// @RepositoryRestResource can be used to directly expose end points for this repository
@Repository
public interface UserRepository extends JpaRepository<User, String> {

    //@Cacheable("books")
    // add this to method to make it cacheable

}
