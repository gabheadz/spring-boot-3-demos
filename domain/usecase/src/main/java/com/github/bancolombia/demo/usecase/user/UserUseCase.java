package com.github.bancolombia.demo.usecase.user;

import com.github.bancolombia.demo.model.user.User;
import com.github.bancolombia.demo.model.user.gateways.UserRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class UserUseCase {

    private final UserRepository userRepository;

    public Flux<User> queryAll(){
        return userRepository.getAll();
    }

}
