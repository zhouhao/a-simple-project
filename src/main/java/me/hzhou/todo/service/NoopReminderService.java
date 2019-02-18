package me.hzhou.todo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.hzhou.todo.domain.Todo;

@Service
@Slf4j
@Profile("test")
public class NoopReminderService implements ReminderService {

    @Override
    public boolean send(String target, @NonNull Todo todo) throws Exception {
        log.info("Send Message: [{}] to target: [{}]", todo, target);
        return true;
    }
}
