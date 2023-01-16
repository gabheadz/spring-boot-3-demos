package com.github.bancolombia.demo.rsocketinterface;

import com.github.bancolombia.demo.model.user.gateways.UserGreeter;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class RSocketGreetingServiceClient implements UserGreeter {

    private final GreetingServiceClient greetingServiceClient;

    @Override
    public Mono<String> saludarUsuario(int idUsuario, String payload) {
        return greetingServiceClient.saludarUsuario(idUsuario, Mono.just(payload));
    }

}
