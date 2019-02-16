package me.hzhou.springdata.schedule;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import me.hzhou.springdata.domain.Todo;
import me.hzhou.springdata.repository.TodoRepository;

@Component
@Slf4j
public class SmsSendScheduler {

    private final TodoRepository todoRepository;

    @Autowired
    public SmsSendScheduler(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Scheduled(fixedRate = 10 * 60 * 1000) // every 10 minutes
    @Transactional
    public void sendSmsReminder() {
        LocalDateTime fromTime = LocalDateTime.now();
        LocalDateTime toTime = fromTime.plusMinutes(30);
        log.info("{} - {}", fromTime, toTime);
        List<Todo> todos = todoRepository.findByCompletedAndRemindTimeBetween(false, fromTime, toTime);
        for (Todo todo : todos) {
            log.info("{}", todo);
        }
    }
}
