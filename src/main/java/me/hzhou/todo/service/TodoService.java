package me.hzhou.todo.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hzhou.todo.domain.Todo;
import me.hzhou.todo.domain.User;
import me.hzhou.todo.domain.dto.TodoDto;
import me.hzhou.todo.repository.TodoRepository;
import me.hzhou.todo.repository.UserRepository;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final UserRepository userRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    public Todo save(TodoDto todoDto) {
        Optional<User> user = userRepository.findById(todoDto.getUserId());
        Todo out = new Todo();
        if (user.isPresent()) {
            out.setRemindTime(todoDto.getRemindTime());
            out.setContent(todoDto.getContent());
            out.setUser(user.get());
            out.setCreatedTime(LocalDateTime.now());
            out = todoRepository.save(out);
        }
        return out;
    }
}
