package me.hzhou.springdata.config;


import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import me.hzhou.springdata.domain.Todo;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Configuration
@Slf4j
public class FluxExample {

    @Bean
    public CommandLineRunner runFluxExample() {
        return args -> {
            EmitterProcessor<Todo> stream = EmitterProcessor.create();
            Mono<List<Todo>> promise = stream
                    .filter(Todo::isCompleted)
                    .doOnNext(s -> log.info("FLUX >>> Todo: {}", s.getContent()))
                    .doOnComplete(() -> log.info("Completed"))
                    .collectList()
                    .subscribeOn(Schedulers.single());

            stream.onNext(new Todo("Read a Book", true));
            stream.onNext(new Todo("Listen Classical Music", true));
            stream.onNext(new Todo("Workout in the Mornings"));
            stream.onNext(new Todo("Organize my room", true));
            stream.onNext(new Todo("Go to the Car Wash", true));
            stream.onNext(new Todo("SP1 2018 is coming", true));
            stream.onComplete();
            promise.block();
        };
    }
}