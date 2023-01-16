package com.github.bancolombia.demo.api;

import com.github.bancolombia.demo.model.user.User;
import com.github.bancolombia.demo.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class Handler {
    private  final UserUseCase userUseCase;

    public Mono<ServerResponse> handleGetUsers(ServerRequest serverRequest) {
        return ServerResponse.ok().body(
                BodyInserters.fromProducer(userUseCase.queryAll(), User.class)
        );
    }

    public Mono<ServerResponse> handleGetUser(ServerRequest serverRequest) {
        String idStr = serverRequest.pathVariable("id");
        return ServerResponse.ok().body(
                BodyInserters.fromProducer(userUseCase.getById(Integer.valueOf(idStr)), User.class)
        );
    }

    public Mono<ServerResponse> handleSaludarUsuario(ServerRequest serverRequest) {
        String idStr = serverRequest.pathVariable("id");
        return userUseCase.saludarUsuario(Integer.parseInt(idStr), "{}")
                .flatMap(texto -> ServerResponse.ok().bodyValue(texto));
    }
}
