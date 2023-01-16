package com.github.bancolombia.demo.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;


@Configuration
public class RouterRest {
@Bean
public RouterFunction<ServerResponse> routerFunction(Handler handler,
                                                     UploadHandler uploadHandler) {
    return route(GET("/api/users"), handler::handleGetUsers)
            .andRoute(GET("/api/users/{id}"), handler::handleGetUser)
            .andRoute(PUT("/api/users/{id}"), handler::handleSaludarUsuario)
            .andRoute(POST("/api/upload"), uploadHandler::handleUpload);

    }
}
