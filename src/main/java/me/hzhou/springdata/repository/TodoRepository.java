package me.hzhou.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.hzhou.springdata.domain.Todo;

public interface TodoRepository extends JpaRepository<Todo, Integer> {

}