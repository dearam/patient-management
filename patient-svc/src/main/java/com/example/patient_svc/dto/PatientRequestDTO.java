package com.example.patient_svc.dto;


import com.example.patient_svc.exception.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequestDTO {

    @NotBlank
    @Size(max = 100, message = "Name must be at most 100 characters")
    private String name;

    @NotBlank
    @Email(groups = CreatePatientValidationGroup.class, message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Date of Birth cannot be blank")
    private String dateOfBirth; // Consider using LocalDate for better type safety

    @NotNull(groups = CreatePatientValidationGroup.class, message = "Registered Date cannot be null")
    private String registeredDate; // Consider using LocalDate for better type safety

}
