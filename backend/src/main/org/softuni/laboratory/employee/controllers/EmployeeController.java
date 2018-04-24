package org.softuni.laboratory.employee.controllers;

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

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping(value = "/register", produces = "application/json")
    public ResponseEntity<?> register(@RequestBody RegisterEmployeeBindingModel employeeBindingModel) {
        if (this.employeeService.employeeExists(employeeBindingModel.getUsername())) {
            return new ResponseEntity<>("Employee already exists.", HttpStatus.BAD_REQUEST);
        }

        if (this.employeeService.save(employeeBindingModel)) {
            return new ResponseEntity<>("Successfully registered employee.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Something went wrong while processing your request...", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

