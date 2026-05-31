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

    private static final int PARTITIONS = 3;

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
        for(int i = 0; i < 500; i++) {
            try {
                log.info("Sending event to Kafka: {}", event);
                int partition = Math.abs(String.valueOf(patient.getId()).hashCode()) % PARTITIONS;
                log.info("Calculated partition: {}", partition);
                String key = String.valueOf(patient.getId()); // Assuming 3 partitions
//                kafkaTemplate.send("patient_topic", partition, key, event.toByteArray());
                kafkaTemplate.send("patient_topic", i%3, key, event.toByteArray());
                Thread.sleep(1000);
            } catch (Exception e) {
                log.info("Failed to send event to Kafka: {}", e.getMessage());
                e.printStackTrace();
            }
        }

    }

}
