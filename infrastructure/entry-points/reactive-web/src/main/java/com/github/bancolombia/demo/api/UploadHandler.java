package com.github.bancolombia.demo.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePartEvent;
import org.springframework.http.codec.multipart.FormPartEvent;
import org.springframework.http.codec.multipart.PartEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class UploadHandler {
    public Mono<ServerResponse> handleUpload(ServerRequest request) {
        Flux<PartEvent> allPartEvents = request.bodyToFlux(PartEvent.class);
        var result = allPartEvents.windowUntil(PartEvent::isLast)
                .concatMap(p -> {
                            return p.switchOnFirst((signal, partEvents) -> {
                                        if (signal.hasValue()) {
                                            PartEvent event = signal.get();
                                            if (event instanceof FormPartEvent formEvent) {
                                                // procesar un campo de formulario
                                                String value = formEvent.value();
                                                log.info("Valor de Formulario : {}", value);
                                                return Mono.just(value + "\n");
                                            } else if (event instanceof FilePartEvent fileEvent) {

                                                // procesar el archivo
                                                String filename = fileEvent.filename();
                                                log.info("Archivo subido : {}", filename);

                                                // Aqui se pueden procesar los FilePartEvent con el contenido
                                                Flux<DataBuffer> contents = partEvents.map(PartEvent::content);
                                                var fileBytes = DataBufferUtils.join(contents)
                                                        .map(dataBuffer -> {
                                                            // Ejemplo de como se pueden leer los bytes del Part
                                                            // obtenido.
                                                            byte[] bytes = new byte[dataBuffer.readableByteCount()];
                                                            dataBuffer.read(bytes);
                                                            DataBufferUtils.release(dataBuffer);
                                                            return bytes;
                                                        });
                                                // Igualmente el Part puede ser reenviado a un servicio upstream si
                                                // asi se requiriera.
                                                return Mono.just(filename);
                                            }

                                            // no signal value
                                            return Mono.error(new RuntimeException("Evento inesperado: " + event));
                                        }
                                        return Flux.empty();
                                    }
                            );
                        }
                );

        return ServerResponse.ok().body(result, String.class);
    }


}
