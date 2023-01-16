package com.github.bancolombia.demo.usecase.user;

import com.github.bancolombia.demo.model.user.Saludo;
import com.github.bancolombia.demo.model.user.User;
import com.github.bancolombia.demo.model.user.gateways.UserGreeter;
import com.github.bancolombia.demo.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;
    private final UserGreeter userGreeter;

    public Flux<User> queryAll(){
        return userRepository.getAll();
    }

    public Mono<User> getById(int id) {
        return userRepository.getById(id);
    }

    public Mono<Saludo> saludarUsuario(int id, String payload) {
        return userGreeter.saludarUsuario(id, payload)
                .map(Saludo::new);
    }
}
