package com.github.bancolombia.demo.consumer;

import com.github.bancolombia.demo.model.user.User;
import com.github.bancolombia.demo.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RestConsumer implements UserRepository {

    private final WebClient client;

    public Flux<User> getAll() {
        return client
            .get()
            .retrieve()
            .bodyToFlux(User.class);
    }

    public Mono<User> getById(int id) {
        return client
                .get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(User.class);
    }

}