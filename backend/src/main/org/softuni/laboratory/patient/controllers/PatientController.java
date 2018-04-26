package org.softuni.laboratory.patient.controllers;

import org.softuni.laboratory.patient.models.binding.RegisterPatientBindingModel;
import org.softuni.laboratory.patient.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody RegisterPatientBindingModel patientBindingModel) {
        if (this.patientService.patientExists(patientBindingModel.getEmail())) {
            return new ResponseEntity<>("Patient already exists.", HttpStatus.BAD_REQUEST);
        }

        if (this.patientService.save(patientBindingModel)) {
            return new ResponseEntity<>("Successfully registered patient with email " + patientBindingModel.getEmail(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Something went wrong while processing your request...", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
