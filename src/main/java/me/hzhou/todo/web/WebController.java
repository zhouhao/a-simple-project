package me.hzhou.todo.web;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import me.hzhou.todo.repository.TodoRepository;
import me.hzhou.todo.repository.UserRepository;

@Controller
@Slf4j
public class WebController {

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    @Autowired
    public WebController(UserRepository userRepository, TodoRepository todoRepository) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("todos", todoRepository.findByRemindTimeAfter(LocalDateTime.now()));
        return "home";
    }
}
