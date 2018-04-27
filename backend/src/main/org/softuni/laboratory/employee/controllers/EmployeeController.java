package org.softuni.laboratory.employee.controllers;

import org.softuni.laboratory.core.entities.exception.ErrorMessage;
import org.softuni.laboratory.core.entities.exception.SuccessMessage;
import org.softuni.laboratory.employee.models.binding.RegisterEmployeeBindingModel;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final ErrorMessage errorMessage;

    private final SuccessMessage successMessage;

    public EmployeeController(EmployeeService employeeService, ErrorMessage errorMessage, SuccessMessage successMessage) {
        this.employeeService = employeeService;
        this.errorMessage = errorMessage;
        this.successMessage = successMessage;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody RegisterEmployeeBindingModel employeeBindingModel) {

        if (this.employeeService.employeeExists(employeeBindingModel.getUsername())) {
            this.errorMessage.setMessage("Employee already exist");
            return new ResponseEntity<>(this.errorMessage, HttpStatus.BAD_REQUEST);
        }

        if (this.employeeService.save(employeeBindingModel)) {
            this.successMessage.setObject(employeeBindingModel);
            return new ResponseEntity<>(this.successMessage, HttpStatus.OK);
        }

        this.errorMessage.setMessage("Something went wrong while processing your request...");
        return new ResponseEntity<>(this.errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

