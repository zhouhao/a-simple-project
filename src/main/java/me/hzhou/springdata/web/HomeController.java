package me.hzhou.springdata.web;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
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
    public Todo create() {
        Todo todo = new Todo();
        todo.setContent("Hello World");
        todo.setPhone("+15083359815");
        todo.setRemindTime(new Timestamp(new Date().getTime()));
        todoRepository.save(todo);
        return todo;
    }
}
