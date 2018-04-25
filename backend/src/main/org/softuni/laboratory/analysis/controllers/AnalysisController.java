package org.softuni.laboratory.analysis.controllers;

import com.google.gson.Gson;
import org.softuni.laboratory.analysis.models.binding.AnalysisCreatedBindingModel;
import org.softuni.laboratory.analysis.models.binding.AnalyzesViewModel;
import org.softuni.laboratory.analysis.services.AnalysisService;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class AnalysisController {

    private final AnalysisService analysisService;

    private final EmployeeService employeeService;

    private final Gson gson;

    public AnalysisController(AnalysisService analysisService, EmployeeService employeeService, Gson gson) {
        this.analysisService = analysisService;
        this.employeeService = employeeService;
        this.gson = gson;
    }

    @PostMapping("/analyses/add")
    public ResponseEntity<?> addAnalyses(@RequestBody AnalysisCreatedBindingModel addAnalysisBindingModel, Principal principal) {

        if (this.analysisService.save(addAnalysisBindingModel, this.employeeService, principal.getName())) {
            return new ResponseEntity<>("Successfully create analysis.", HttpStatus.OK);
        }

        return new ResponseEntity<>("Something went wrong while processing your request...", HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @GetMapping(value = "/analyzes", produces = "application/json")
    public @ResponseBody String allAnalyzes() {
        return this.gson.toJson(this.analysisService.getAllAnalyzes());
    }

    @PostMapping(value = "/analyses/{id}/edit", produces = "application/json")
    public ResponseEntity<?> editAnalyses(@PathVariable String id){
        return null;
    }

    @GetMapping(value = "/analyzes/{id}", produces = "application/json")
    public ResponseEntity<?> getOneAnalyses(@PathVariable String id){
        AnalyzesViewModel model = this.analysisService.getOneById(id);

        if(model == null){
            return new ResponseEntity<>("Analyses doesn't exist", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
