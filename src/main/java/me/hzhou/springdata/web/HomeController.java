package me.hzhou.springdata.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import me.hzhou.springdata.domain.Todo;
import me.hzhou.springdata.repository.TodoRepository;
import reactor.core.publisher.Flux;

@RestController
public class HomeController {

    private final TodoRepository todoRepository;

    @Autowired
    public HomeController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer id) {
        Optional<Todo> out = todoRepository.findById(id);
        return out.isPresent() ? ResponseEntity.ok(out.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/todo")
    public Flux<Todo> getTodos() {
        return null;
    }
}
