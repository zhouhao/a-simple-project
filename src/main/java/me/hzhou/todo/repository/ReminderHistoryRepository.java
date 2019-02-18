package me.hzhou.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.hzhou.todo.domain.ReminderHistory;
import me.hzhou.todo.domain.User;

public interface ReminderHistoryRepository extends JpaRepository<ReminderHistory, Integer> {
    ReminderHistory findFirstByUserOrderByIdDesc(User user);
}