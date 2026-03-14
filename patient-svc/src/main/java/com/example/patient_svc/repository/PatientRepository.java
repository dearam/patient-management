package com.example.patient_svc.repository;

import com.example.patient_svc.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndIdNot(String email, String id);
}
