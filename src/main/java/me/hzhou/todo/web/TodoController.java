package me.hzhou.todo.web;

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

import me.hzhou.todo.domain.Todo;
import me.hzhou.todo.domain.dto.TodoDto;
import me.hzhou.todo.repository.TodoRepository;
import me.hzhou.todo.service.ReminderService;
import me.hzhou.todo.service.TodoService;

@RestController
public class TodoController {

    private final TodoRepository todoRepository;
    private final TodoService todoService;
    private final ReminderService reminderService;

    @Autowired
    public TodoController(TodoRepository todoRepository, TodoService todoService, ReminderService reminderService) {
        this.todoRepository = todoRepository;
        this.todoService = todoService;
        this.reminderService = reminderService;
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer id) {
        Optional<Todo> out = todoRepository.findById(id);
        return out.isPresent() ? ResponseEntity.ok(out.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/todo/{id}/delay")
    public ResponseEntity<Todo> delay(@PathVariable Integer id) {
        Optional<Todo> out = todoRepository.findById(id);
        if (out.isPresent()) {
            Todo todo = out.get();
            todo.setRemindTime(todo.getRemindTime().plusMinutes(30));
            return ResponseEntity.ok(todoRepository.save(todo));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/todo/{id}/complete")
    public ResponseEntity<Todo> complete(@PathVariable Integer id) {
        Optional<Todo> out = todoRepository.findById(id);
        if (out.isPresent()) {
            Todo todo = out.get();
            todo.setCompleted(true);
            return ResponseEntity.ok(todoRepository.save(todo));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/todo/{id}/sms")
    public ResponseEntity<Todo> sms(@PathVariable Integer id) throws Exception {
        Optional<Todo> out = todoRepository.findById(id);
        if (out.isPresent()) {
            reminderService.send(out.get().getUser().getPhone(), out.get());
            return ResponseEntity.ok(out.get());
        }
        return ResponseEntity.notFound().build();
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
