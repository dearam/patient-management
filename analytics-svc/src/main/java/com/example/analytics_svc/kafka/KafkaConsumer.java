package com.example.analytics_svc.kafka;

import com.example.analytics_svc.Service.PatientRecordService;
import com.example.analytics_svc.dto.PatientRequestDTO;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Slf4j
@Service
public class KafkaConsumer {

    @Value("${server.port}")
    private String port;

    @Autowired
    PatientRecordService patientRecordService;

    @KafkaListener(topics = "patient_topic")
    public void consumeEvent(byte[] event, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                             @Header(KafkaHeaders.OFFSET) long offset,
                             @Header(KafkaHeaders.RECEIVED_KEY) String key) {

        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);

            System.out.println(
                    "Consumer Instance: " + port +
                            " | Partition: " + partition +
                            " | Offset: " + offset +
                            " | Key: " + key
            );

            log.info("Received patient event: [id={}, name={}, email={}]",
                    patientEvent.getId(),
                    patientEvent.getName(),
                    patientEvent.getEmail());
            PatientRequestDTO requestDTO = new PatientRequestDTO(patientEvent.getName(), patientEvent.getEmail());
            patientRecordService.createPatientRecord(requestDTO);
        } catch (InvalidProtocolBufferException e) {
            log.error("Failed to parse protobuf message", e);
        }
    }
}