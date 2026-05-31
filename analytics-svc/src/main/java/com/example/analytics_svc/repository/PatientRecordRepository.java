package com.example.analytics_svc.repository;

import com.example.analytics_svc.Model.PatientRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecord, String> {
}

