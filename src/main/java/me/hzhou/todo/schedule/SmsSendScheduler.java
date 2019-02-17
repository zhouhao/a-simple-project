package me.hzhou.todo.schedule;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.plivo.api.exceptions.PlivoRestException;
import lombok.extern.slf4j.Slf4j;
import me.hzhou.todo.domain.Todo;
import me.hzhou.todo.repository.TodoRepository;
import me.hzhou.todo.service.PlivoService;

@Component
@Slf4j
public class SmsSendScheduler {

    private final TodoRepository todoRepository;
    private final PlivoService plivoService;

    @Autowired
    public SmsSendScheduler(TodoRepository todoRepository, PlivoService plivoService) {
        this.todoRepository = todoRepository;
        this.plivoService = plivoService;
    }

    @Scheduled(fixedRate = 10 * 60 * 1000) // every 10 minutes
    @Transactional
    public void sendSmsReminder() throws IOException, PlivoRestException {
        LocalDateTime fromTime = LocalDateTime.now();
        LocalDateTime toTime = fromTime.plusMinutes(30);
        log.info("{} - {}", fromTime, toTime);
        List<Todo> todos = todoRepository.findByCompletedAndRemindTimeBetween(false, fromTime, toTime);
        for (Todo todo : todos) {
            log.info("{}", todo);
        }

        // plivoService.send("Hello World", "${target}");
    }
}
