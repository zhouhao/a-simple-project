package me.hzhou.todo.repository;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;

import me.hzhou.todo.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findFirstByPhone(@NotNull String phone);

    User findFirstByNameOrPhone(String name, String phone);
}