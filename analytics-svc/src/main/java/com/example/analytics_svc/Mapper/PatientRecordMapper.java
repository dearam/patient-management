package com.example.analytics_svc.Mapper;

import com.example.analytics_svc.Model.PatientRecord;
import com.example.analytics_svc.dto.PatientRequestDTO;

public class PatientRecordMapper {
    public static PatientRecord toEntity(PatientRequestDTO patientRequestDTO) {
        PatientRecord patientRecord = new PatientRecord();
        patientRecord.setName(patientRequestDTO.getName());
        patientRecord.setEmail(patientRequestDTO.getEmail());
        return patientRecord;
    }

    public static PatientRequestDTO toDTO(PatientRecord patientRecord) {
        PatientRequestDTO patientRequestDTO = new PatientRequestDTO();
        patientRequestDTO.setName(patientRecord.getName());
        patientRequestDTO.setEmail(patientRecord.getEmail());
        return patientRequestDTO;
    }
}
