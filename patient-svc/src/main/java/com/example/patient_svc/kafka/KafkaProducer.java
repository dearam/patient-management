package com.example.patient_svc.kafka;

import com.example.patient_svc.Model.Patient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sentEvent(Patient patient) {
        PatientEvent event = PatientEvent.newBuilder()
                .setId(patient.getId())
                .setName(patient.getName())
                .setEmail(patient.getEmail())
                .setEventType("PATIENT_CREATED")
                .build();

        try {
            log.info("Sending event to Kafka: {}", event);
            kafkaTemplate.send("patient_topic", event.toByteArray());
        } catch (Exception e) {
            log.info("Failed to send event to Kafka: {}", e.getMessage());
            e.printStackTrace();
        }

    }

}
