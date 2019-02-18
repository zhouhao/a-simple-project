package me.hzhou.todo.service;

import lombok.NonNull;
import me.hzhou.todo.domain.Todo;

public interface ReminderService {

    boolean send(String target, @NonNull Todo todo) throws Exception;

}
