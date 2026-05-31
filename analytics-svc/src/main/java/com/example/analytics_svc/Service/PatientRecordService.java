package com.example.analytics_svc.Service;

import com.example.analytics_svc.Mapper.PatientRecordMapper;
import com.example.analytics_svc.Model.PatientRecord;
import com.example.analytics_svc.dto.PatientRequestDTO;
import com.example.analytics_svc.repository.PatientRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PatientRecordService {

    @Autowired
    PatientRecordRepository patientRecordRepository;

    public void createPatientRecord(PatientRequestDTO patientRequestDTO) {
        PatientRecord patientRecord = PatientRecordMapper.toEntity(patientRequestDTO);
        log.info("Creating patient record: [name={}, email={}]", patientRecord.getName(), patientRecord.getEmail());
        patientRecordRepository.save(patientRecord);
        log.info("Patient record created successfully for email: {}", patientRecord.getEmail());
    }
}
