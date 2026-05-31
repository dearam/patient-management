package com.example.analytics_svc.Model;

import com.example.analytics_svc.Enum.TrackingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)  // ← auto UUID
    private String patientId;           // same UUID from patient service

    private String name;
    private String email;
    private Integer age;
    private String gender;
    private String doctorId;

    private Instant registeredAt;       // when patient was created

    @Enumerated(EnumType.STRING)
    private TrackingStatus trackingStatus;  // ACTIVE, DISCHARGED, PAUSED

    // These grow over time as reports come in:
    private Integer totalReportsCount;
    private Instant lastReportDate;
    private Instant lastUpdatedAt;
}
