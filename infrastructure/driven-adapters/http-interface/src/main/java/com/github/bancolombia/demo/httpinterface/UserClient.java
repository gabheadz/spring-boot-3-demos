package com.github.bancolombia.demo.httpinterface;


import com.github.bancolombia.demo.model.user.User;
import com.github.bancolombia.demo.model.user.gateways.UserRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@HttpExchange(url = "/users", accept = "application/json", contentType = "application/json")
public interface UserClient extends UserRepository {
    @GetExchange("/")
    Flux<User> getAll();

    @GetExchange("/{id}")
    Mono<User> getById(@PathVariable("id") int id);

}