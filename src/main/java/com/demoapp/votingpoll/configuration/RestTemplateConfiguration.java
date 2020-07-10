package com.demoapp.votingpoll.configuration;

import com.demoapp.votingpoll.handler.RestTemplateCustomErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateCustomErrorHandler());
        return restTemplate;
    }

}
