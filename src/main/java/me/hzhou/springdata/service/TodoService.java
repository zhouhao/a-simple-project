package me.hzhou.springdata.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hzhou.springdata.domain.Todo;
import me.hzhou.springdata.domain.User;
import me.hzhou.springdata.domain.dto.TodoDto;
import me.hzhou.springdata.repository.TodoRepository;
import me.hzhou.springdata.repository.UserRepository;

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
