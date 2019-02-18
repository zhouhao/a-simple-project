package me.hzhou.todo.schedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import me.hzhou.todo.domain.Todo;
import me.hzhou.todo.repository.TodoRepository;
import me.hzhou.todo.service.ReminderService;

@Component
@Slf4j
public class SmsSendScheduler {

    private final TodoRepository todoRepository;
    private final ReminderService reminderService;

    @Autowired
    public SmsSendScheduler(TodoRepository todoRepository, ReminderService reminderService) {
        this.todoRepository = todoRepository;
        this.reminderService = reminderService;
    }

    @Scheduled(fixedRate = 10 * 60 * 1000) // every 10 minutes
    @Transactional
    public void sendSmsReminder() throws Exception {
        LocalDateTime fromTime = LocalDateTime.now();
        LocalDateTime toTime = fromTime.plusMinutes(30);
        log.info("{} - {}", fromTime, toTime);
        List<Todo> todos = todoRepository.findByCompletedAndRemindTimeBetween(false, fromTime, toTime);
        for (Todo todo : todos) {
            log.info("{}", todo);
            reminderService.send(todo.getUser().getPhone(), todo);
        }
    }
}
