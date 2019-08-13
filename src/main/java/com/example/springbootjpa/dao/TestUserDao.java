package com.example.springbootjpa.dao;

import com.example.springbootjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TestUserDao extends JpaRepository<User, Integer> {


    @Query("select t from User t where t.username = ?1")
    User findByUsername(String username);


    @Query("select t from User t where t.username = ?1 and t.age=?2")
    User findByUsernameAAndAge(String username,int age);


}

