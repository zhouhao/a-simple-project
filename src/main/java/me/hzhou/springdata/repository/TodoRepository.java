package me.hzhou.springdata.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import me.hzhou.springdata.domain.Todo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoRepository extends ReactiveCrudRepository<Todo, Integer> {

    Mono<Todo> findById(Integer id);

    Flux<Todo> findAll();
}