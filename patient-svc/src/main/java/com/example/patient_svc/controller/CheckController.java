package com.example.patient_svc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("/health")
    public String healthCheck() {
        return "Patient Service is UP";
    }
}
