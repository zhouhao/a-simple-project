package me.hzhou.todo.service;

public interface ReminderService {
    boolean send(String target, String message) throws Exception;
}
