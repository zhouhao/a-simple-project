package me.hzhou.todo.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Profile("test")
public class NoopReminderService implements ReminderService {

    @Override
    public boolean send(String target, String message) throws Exception {
        log.info("Send Message: [{}] to target: [{}]", message, target);
        return true;
    }
}
