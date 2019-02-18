package me.hzhou.todo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.hzhou.todo.domain.ReminderHistory;
import me.hzhou.todo.domain.Todo;
import me.hzhou.todo.repository.ReminderHistoryRepository;

@Aspect
@Component
@Slf4j
public class ReminderSendLogger {

    private final ReminderHistoryRepository reminderHistoryRepository;

    @Autowired
    public ReminderSendLogger(ReminderHistoryRepository reminderHistoryRepository) {
        this.reminderHistoryRepository = reminderHistoryRepository;
    }

    @AfterReturning(value = "execution(* me.hzhou.todo.service.ReminderService.send(..))", returning = "result")
    public void logReminder(JoinPoint jp, Object result) {
        Todo todo = (Todo) jp.getArgs()[1];
        ReminderHistory rh = new ReminderHistory();
        rh.setTodo(todo);
        rh.setUser(todo.getUser());
        reminderHistoryRepository.save(rh);

    }
}
