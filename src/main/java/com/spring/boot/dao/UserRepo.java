package com.spring.boot.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.boot.model.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
