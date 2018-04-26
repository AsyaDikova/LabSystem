package org.softuni.laboratory.occurrence.controllers;

import org.softuni.laboratory.core.entities.exception.ErrorMessage;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.softuni.laboratory.occurrence.models.binding.CreateOccurrenceBindingModel;
import org.softuni.laboratory.occurrence.services.OccurrenceService;
import org.softuni.laboratory.patient.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/occurrence")
public class OccurrenceController {

    private final PatientService patientService;

    private final EmployeeService employeeService;

    private final OccurrenceService occurrenceService;

    public OccurrenceController(PatientService patientService, EmployeeService employeeService, OccurrenceService occurrenceService) {
        this.patientService = patientService;
        this.employeeService = employeeService;
        this.occurrenceService = occurrenceService;
    }


    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<?> addAnalyses(@RequestBody CreateOccurrenceBindingModel occurrenceBindingModel){
        if(!this.patientService.patientExists(occurrenceBindingModel.getEmailPatient())){
            return new ResponseEntity<>(new ErrorMessage("Patient not exist", false), HttpStatus.BAD_REQUEST);
        }

        if(!this.employeeService.employeeExists(occurrenceBindingModel.getUsernameEmployee())){
            return new ResponseEntity<>(new ErrorMessage("Employee not exist", false), HttpStatus.BAD_REQUEST);
        }

        if(this.occurrenceService.save(occurrenceBindingModel, this.employeeService, this.patientService)){
            return new ResponseEntity<>(new ErrorMessage("Occurrence is success create", true), HttpStatus.CREATED);
        }

        return new ResponseEntity<>("Something went wrong while processing your request...", HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
