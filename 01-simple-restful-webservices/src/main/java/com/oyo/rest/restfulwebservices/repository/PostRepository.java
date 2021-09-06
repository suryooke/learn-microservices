package com.oyo.rest.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oyo.rest.restfulwebservices.model.user.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
