package com.github.bancolombia.demo.model.user.gateways;

import reactor.core.publisher.Mono;

public interface UserGreeter {
    /**
     * Metodo que devuelve un saludo para un ID de usuario especifico.
     * @param idUsuario El ID de usuario a buscar para obtener el nombre
     * @return Devuelve un saludo Ej: "Hola [nombre de usuario obtenido]!"
     */
    Mono<String> saludarUsuario(int idUsuario, String payload);
}
