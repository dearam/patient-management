package com.example.analytics_svc.Enum;

public enum TrackingStatus {
    ACTIVE,     // Patient is actively being monitored
    DISCHARGED, // Patient has been discharged and is no longer monitored
    PAUSED      // Monitoring is temporarily paused (e.g., patient is in hospital)
}
