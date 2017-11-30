package com.mine.myboot.datajpa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mine.myboot.datajpa.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findById(long id);

	Long deleteById(Long id);
}
