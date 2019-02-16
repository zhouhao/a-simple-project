package me.hzhou.springdata.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.hzhou.springdata.domain.Todo;
import me.hzhou.springdata.domain.dto.TodoDto;
import me.hzhou.springdata.repository.TodoRepository;
import me.hzhou.springdata.service.TodoService;

@RestController
public class HomeController {

    private final TodoRepository todoRepository;
    private final TodoService todoService;

    @Autowired
    public HomeController(TodoRepository todoRepository, TodoService todoService) {
        this.todoRepository = todoRepository;
        this.todoService = todoService;
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer id) {
        Optional<Todo> out = todoRepository.findById(id);
        return out.isPresent() ? ResponseEntity.ok(out.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{id}/todo")
    public ResponseEntity<List<Todo>> getTodoByUser(@PathVariable("id") Integer userId) {
        List<Todo> out = todoRepository.findByUserId(userId);
        return ResponseEntity.ok(out);
    }

    @PostMapping("/todo")
    public ResponseEntity<Todo> create(@Valid @RequestBody TodoDto todo) {
        return ResponseEntity.ok(todoService.save(todo));
    }
}
