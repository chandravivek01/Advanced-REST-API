package com.vcs.advancedRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vcs.advancedRestApi.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
