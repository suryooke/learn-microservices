package com.oyo.rest.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oyo.rest.restfulwebservices.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
