package me.hzhou.todo.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import me.hzhou.todo.config.ReminderProp;
import me.hzhou.todo.domain.ReceivedSms;
import me.hzhou.todo.domain.ReminderHistory;
import me.hzhou.todo.domain.Todo;
import me.hzhou.todo.domain.User;
import me.hzhou.todo.domain.dto.TodoDto;
import me.hzhou.todo.domain.dto.UserDto;
import me.hzhou.todo.repository.ReceivedSmsRepository;
import me.hzhou.todo.repository.ReminderHistoryRepository;
import me.hzhou.todo.repository.TodoRepository;
import me.hzhou.todo.repository.UserRepository;
import me.hzhou.todo.service.ReminderService;
import me.hzhou.todo.service.TodoService;

@RestController
@Slf4j
public class TodoController {

    private final TodoRepository todoRepository;
    private final TodoService todoService;
    private final ReminderService reminderService;
    private final ReminderProp reminderProp;
    private final UserRepository userRepository;
    private final ReminderHistoryRepository reminderHistoryRepository;
    private final ReceivedSmsRepository receivedSmsRepository;

    @Autowired
    public TodoController(TodoRepository todoRepository, TodoService todoService,
                          ReminderService reminderService, ReminderProp reminderProp,
                          UserRepository userRepository, ReminderHistoryRepository reminderHistoryRepository,
                          ReceivedSmsRepository receivedSmsRepository) {
        this.todoRepository = todoRepository;
        this.todoService = todoService;
        this.reminderService = reminderService;
        this.reminderProp = reminderProp;
        this.userRepository = userRepository;
        this.reminderHistoryRepository = reminderHistoryRepository;
        this.receivedSmsRepository = receivedSmsRepository;
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

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(todoService.save(user));
    }

    @PutMapping("/user")
    public ResponseEntity<User> updateUser(@Valid @RequestBody UserDto user) {
        return ResponseEntity.ok(todoService.update(user));
    }

    @PostMapping("/sms")
    public ResponseEntity<Todo> receive(@RequestParam("From") String from,
                                        @RequestParam("To") String to,
                                        @RequestParam("Text") String message) {
        from = from.startsWith("+") ? from : "+" + from;
        log.info("from = {}, to = {}, message = {}", from, to, message);
        if (StringUtils.isEmpty(message)) {
            return ResponseEntity.noContent().build();
        }
        receivedSmsRepository.save(new ReceivedSms(from, to, message));
        User user = userRepository.findFirstByPhone(from);
        if (user == null) {
            log.warn("User not found by phone = {}", from);
            return ResponseEntity.noContent().build();
        }
        ReminderHistory rh = reminderHistoryRepository.findFirstByUserOrderByIdDesc(user);
        if (rh == null) {
            log.warn("reminder history not found for user = {}", user.getId());
            return ResponseEntity.noContent().build();
        }
        message = message.toLowerCase();
        if (message.contains(reminderProp.getStopWord().toLowerCase())) {
            Todo todo = rh.getTodo();
            todo.setCompleted(true);
            return ResponseEntity.ok(todoRepository.save(todo));
        }
        if (message.contains(reminderProp.getDelay().toLowerCase())) {
            Todo todo = rh.getTodo();
            todo.setRemindTime(todo.getRemindTime().plusMinutes(30));
            return ResponseEntity.ok(todoRepository.save(todo));
        }
        return ResponseEntity.noContent().build();
    }
}
