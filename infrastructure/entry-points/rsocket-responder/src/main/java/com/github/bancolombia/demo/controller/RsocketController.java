package com.github.bancolombia.demo.controller;

import com.github.bancolombia.demo.usecase.user.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class RsocketController {

     private final UserUseCase useCase;

    // interaction model Request/Response
    @MessageMapping("greeting/{id}")
    public Mono<String> saludarUsuario(@DestinationVariable("id") int id,
                                       Mono<String> payload) {
        return payload
                .flatMap(payloadData -> useCase.getById(id))
                .map(userData -> "Hello " + userData.getName())
                ;
    }

    /*
    // interaction model Request/Stream
    @MessageMapping(value = "route.request.stream")
    public Flux<Object*/
/* change for object response *//*
> getRequestStream() {
        // return useCase.doAction();
        return Flux.empty();
    }

    // interaction model Fire-and-Forget
    @MessageMapping(value = "route.fire.forget")
    public Mono<Void> getRequetsFireForget(Object objRequest*/
/* change for object request *//*
) {
        // return useCase.doAction(objRequest);
        return Mono.empty();
    }

    // interaction model Channel
    @MessageMapping(value = "route.channel")
    public Flux<Object*/
/* change for object response *//*
> getChannel(Flux<Object*/
/* change for object request *//*
> objRequest) {
        // return useCase.doAction(objRequest);
        return Flux.empty();
    }
*/

}