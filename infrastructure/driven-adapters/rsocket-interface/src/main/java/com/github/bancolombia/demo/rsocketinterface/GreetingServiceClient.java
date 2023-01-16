package com.github.bancolombia.demo.rsocketinterface;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.service.RSocketExchange;
import reactor.core.publisher.Mono;

public interface GreetingServiceClient {

    @RSocketExchange("greeting/{id}")
    Mono<String> saludarUsuario(@DestinationVariable("id") int id, @Payload Mono<String> payload);

}
