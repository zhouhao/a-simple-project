package me.hzhou.springdata.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import me.hzhou.springdata.domain.Todo;
import me.hzhou.springdata.repository.TodoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HomeController {

    private final TodoRepository todoRepository;

    @Autowired
    public HomeController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping("/todo/{id}")
    public Mono<Todo> getTodo(@PathVariable Integer id) {
        return todoRepository.findById(id);
    }

    @GetMapping("/todo")
    public Flux<Todo> getTodos() {
        return null;
    }
}
