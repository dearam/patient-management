package com.example.patient_svc.Service;

import com.example.patient_svc.Model.Patient;
import com.example.patient_svc.dto.PatientRequestDTO;
import com.example.patient_svc.dto.PatientResponseDTO;
import com.example.patient_svc.exception.EmailAlreadyExistsException;
import com.example.patient_svc.exception.PatientNotFoundException;
import com.example.patient_svc.grpc.BillingServiceClient;
import com.example.patient_svc.kafka.KafkaProducer;
import com.example.patient_svc.mapper.PatientMapper;
import com.example.patient_svc.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private BillingServiceClient billingServiceClient;
    private KafkaProducer kafkaProducer;

    public PatientService(PatientRepository patientRepository, BillingServiceClient billingServiceClient, KafkaProducer kafkaProducer) {
        this.patientRepository = patientRepository;
        this.kafkaProducer = kafkaProducer;
        this.billingServiceClient = billingServiceClient;
    }

    public List<PatientResponseDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toDTO).toList();
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO){
        if(patientRepository.existsByEmail(patientRequestDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists: " + patientRequestDTO.getEmail());
        }
        Patient patient = patientRepository.save(PatientMapper.toModel(patientRequestDTO));

        billingServiceClient.createBillingAccount(patient.getId().toString(), patient.getName(), patient.getEmail());
        kafkaProducer.sentEvent(patient);
        return PatientMapper.toDTO(patient);
    }

    public PatientResponseDTO updatePatient(String id,PatientRequestDTO requestDTO){
        Patient patient = patientRepository.findById(id).orElseThrow(()-> new PatientNotFoundException("Patient not found with id: " + id));
//        if(patientRepository.existsByEmail(requestDTO.getEmail())){
//            throw new EmailAlreadyExistsException("Email already exists: " + requestDTO.getEmail());
//        }
        patient.setName(requestDTO.getName());
        patient.setEmail(requestDTO.getEmail());
        patient.setAddress(requestDTO.getAddress());
        patient.setDateOfBirth(LocalDate.parse(requestDTO.getDateOfBirth()));
        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(String id){
        if(!patientRepository.existsById(id)){
            throw new PatientNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }
}
