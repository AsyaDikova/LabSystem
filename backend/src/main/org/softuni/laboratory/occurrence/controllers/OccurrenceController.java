package org.softuni.laboratory.occurrence.controllers;

import org.softuni.laboratory.core.entities.exception.ErrorMessage;
import org.softuni.laboratory.core.entities.exception.SuccessMessage;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.softuni.laboratory.occurrence.models.binding.CreateOccurrenceBindingModel;
import org.softuni.laboratory.occurrence.models.binding.OccurrenceViewModel;
import org.softuni.laboratory.occurrence.services.OccurrenceService;
import org.softuni.laboratory.patient.services.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/occurrence")
public class OccurrenceController {

    private final PatientService patientService;

    private final EmployeeService employeeService;

    private final OccurrenceService occurrenceService;

    private final ErrorMessage errorMessage;

    private final SuccessMessage successMessage;

    public OccurrenceController(PatientService patientService, EmployeeService employeeService, OccurrenceService occurrenceService, ErrorMessage errorMessage, SuccessMessage successMessage) {
        this.patientService = patientService;
        this.employeeService = employeeService;
        this.occurrenceService = occurrenceService;
        this.errorMessage = errorMessage;
        this.successMessage = successMessage;
    }


    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<?> addOccurrences(@ModelAttribute CreateOccurrenceBindingModel occurrenceBindingModel){
        if(!this.patientService.patientExists(occurrenceBindingModel.getEmailPatient())){
            this.errorMessage.setMessage("Patient not exist");
            return new ResponseEntity<>(this.errorMessage, HttpStatus.BAD_REQUEST);
        }

        if(!this.employeeService.employeeExists(occurrenceBindingModel.getUsernameEmployee())){
            this.errorMessage.setMessage("Employee not exist");
            return new ResponseEntity<>(this.errorMessage, HttpStatus.BAD_REQUEST);
        }

        OccurrenceViewModel saveObject = this.occurrenceService.save(occurrenceBindingModel, this.employeeService, this.patientService);

        if(saveObject != null){
            this.successMessage.setObject(saveObject);
            return new ResponseEntity<>(this.successMessage, HttpStatus.CREATED);
        }

        this.errorMessage.setMessage("Something went wrong while processing your request...");
        return new ResponseEntity<>(this.errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
