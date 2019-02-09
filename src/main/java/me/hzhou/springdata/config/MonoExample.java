package me.hzhou.springdata.config;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import me.hzhou.springdata.domain.Todo;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoProcessor;
import reactor.core.scheduler.Schedulers;

@Configuration
@Slf4j
public class MonoExample {
    @Bean
    public CommandLineRunner runMonoExample() {
        return args -> {
            MonoProcessor<Todo> promise = MonoProcessor.create();
            Mono<Todo> result = promise
                    .doOnSuccess(p -> log.info("MONO >> ToDo: {}", p.getContent()))
                    .doOnTerminate(() -> log.info("MONO >> Done"))
                    .doOnError(t -> log.error(t.getMessage(), t))
                    .subscribeOn(Schedulers.single());
            promise.onNext(new Todo("Buy my ticket for SpringOne Platform 2018"));
            //promise.onError(new IllegalArgumentException("There is an error processing the Todo..."));
            result.block(Duration.ofMillis(1000));
        };
    }
}