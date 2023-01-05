package com.github.bancolombia.demo.model.user.gateways;

import com.github.bancolombia.demo.model.user.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {
    Flux<User> getAll();
    Mono<User> getById(int id);
}
