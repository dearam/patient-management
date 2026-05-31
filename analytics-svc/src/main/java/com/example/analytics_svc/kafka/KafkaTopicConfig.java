package com.example.analytics_svc.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Bean
    public NewTopic patientTopic() {

        return TopicBuilder.name("patient_topic")
                .partitions(3)
                .replicas(3) // use 1 for local single broker
                .build();
    }
}