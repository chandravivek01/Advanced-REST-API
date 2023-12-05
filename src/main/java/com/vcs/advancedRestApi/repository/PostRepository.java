package com.vcs.advancedRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcs.advancedRestApi.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
