package me.hzhou.springdata.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.hzhou.springdata.domain.Todo;
import me.hzhou.springdata.repository.TodoRepository;

@RestController
public class HomeController {

    private final TodoRepository todoRepository;

    @Autowired
    public HomeController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todo")
    public ResponseEntity<Todo> create(@Valid Todo todo) {
        return ResponseEntity.ok(todoRepository.save(todo));
    }
}
