package me.hzhou.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.hzhou.springdata.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}