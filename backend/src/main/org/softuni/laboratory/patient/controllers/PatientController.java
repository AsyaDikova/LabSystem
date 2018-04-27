package org.softuni.laboratory.patient.controllers;

import org.softuni.laboratory.core.entities.exception.ErrorMessage;
import org.softuni.laboratory.core.entities.exception.SuccessMessage;
import org.softuni.laboratory.patient.models.binding.RegisterPatientBindingModel;
import org.softuni.laboratory.patient.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    private final ErrorMessage errorMessage;

    private final SuccessMessage successMessage;

    public PatientController(PatientService patientService, ErrorMessage errorMessage, SuccessMessage successMessage) {
        this.patientService = patientService;
        this.errorMessage = errorMessage;
        this.successMessage = successMessage;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody RegisterPatientBindingModel patientBindingModel) {
        if (this.patientService.patientExists(patientBindingModel.getEmail())) {
            this.errorMessage.setMessage("Patient already exists.");
            return new ResponseEntity<>(this.errorMessage, HttpStatus.BAD_REQUEST);
        }

        if (this.patientService.save(patientBindingModel)) {
            this.successMessage.setObject( new Object());
            return new ResponseEntity<>(this.successMessage, HttpStatus.OK);
        }
        this.errorMessage.setMessage("Something went wrong while processing your request...");
        return new ResponseEntity<>(this.errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
