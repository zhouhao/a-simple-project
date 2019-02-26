package me.hzhou.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import me.hzhou.todo.domain.ReceivedSms;
import me.hzhou.todo.domain.ReminderHistory;
import me.hzhou.todo.domain.User;

public interface ReceivedSmsRepository extends JpaRepository<ReceivedSms, Integer> {

}