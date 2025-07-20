package com.sportygroup.bets.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.sportygroup.bets.config.TopicConstant.EVENT_OUTCOMES;


@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder
            .name(EVENT_OUTCOMES)
            .build();
    }
}
