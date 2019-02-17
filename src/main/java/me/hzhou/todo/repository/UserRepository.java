package me.hzhou.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.hzhou.todo.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}