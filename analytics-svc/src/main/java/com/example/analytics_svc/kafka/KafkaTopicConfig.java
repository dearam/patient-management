package com.example.analytics_svc.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic patientTopic() {
        return new NewTopic("patient_topic", 1, (short) 1);
    }
}

