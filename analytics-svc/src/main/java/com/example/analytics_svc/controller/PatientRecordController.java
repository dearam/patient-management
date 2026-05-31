package com.example.analytics_svc.controller;

import com.example.analytics_svc.Model.PatientRecord;
import com.example.analytics_svc.dto.PatientRequestDTO;
import com.example.analytics_svc.dto.PatientResponseDTO;
import com.example.analytics_svc.repository.PatientRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient-records")
public class PatientRecordController {

    @Autowired
    PatientRecordRepository patientRecordRepository;

    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getPatientRecord() {
        // Placeholder for getting a patient record
        List<PatientRecord> record = patientRecordRepository.findAll();
        return ResponseEntity.ok().body(record.stream().map(patientRecord -> {
            PatientResponseDTO responseDTO = new PatientResponseDTO();
            responseDTO.setName(patientRecord.getName());
            responseDTO.setEmail(patientRecord.getEmail());
            return responseDTO;
        }).toList());
    }

    @GetMapping
    @RequestMapping("/health")
    public String healthCheck() {
        // Placeholder for health check endpoint
        return "Analytics Service is UP";
    }

}
