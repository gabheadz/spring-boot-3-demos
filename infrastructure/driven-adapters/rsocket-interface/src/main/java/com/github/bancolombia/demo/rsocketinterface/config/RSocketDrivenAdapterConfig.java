package com.github.bancolombia.demo.rsocketinterface.config;

import com.github.bancolombia.demo.model.user.gateways.UserGreeter;
import com.github.bancolombia.demo.rsocketinterface.GreetingServiceClient;
import com.github.bancolombia.demo.rsocketinterface.RSocketGreetingServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.service.RSocketServiceProxyFactory;

@Configuration
public class RSocketDrivenAdapterConfig {
    @Bean
    public UserGreeter userGreeter(RSocketRequester.Builder requesterBuilder) {
        RSocketRequester rsocketRequester = requesterBuilder.tcp("localhost", 7000);
        RSocketServiceProxyFactory factory = RSocketServiceProxyFactory.builder(rsocketRequester).build();
        GreetingServiceClient greetingServiceClient = factory.createClient(GreetingServiceClient.class);
        return new RSocketGreetingServiceClient(greetingServiceClient);
    }
}
