package com.github.bancolombia.demo.config;

import com.github.bancolombia.demo.consumer.RestConsumer;
import com.github.bancolombia.demo.model.user.gateways.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScan(basePackages = "com.github.bancolombia.demo.usecase",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
public class UseCasesConfig {

        @Bean
        public UserRepository userRepository(WebClient webClient) {
                return new RestConsumer(webClient);
        }
}
