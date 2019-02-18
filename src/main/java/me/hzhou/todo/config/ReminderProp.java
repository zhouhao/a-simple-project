package me.hzhou.todo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "reminder")
@Getter
@Setter
public class ReminderProp {
    private String stopWord;
    private String delay;
}
