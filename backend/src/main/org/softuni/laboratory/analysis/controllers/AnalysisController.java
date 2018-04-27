package org.softuni.laboratory.analysis.controllers;

import com.google.gson.Gson;
import org.softuni.laboratory.analysis.models.binding.AnalysisCreatedBindingModel;
import org.softuni.laboratory.analysis.models.binding.AnalyzesViewModel;
import org.softuni.laboratory.analysis.services.AnalysisService;
import org.softuni.laboratory.core.entities.exception.ErrorMessage;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnalysisController {

    private final AnalysisService analysisService;

    private final EmployeeService employeeService;

    private final Gson gson;

    private final ErrorMessage errorMessage;

    public AnalysisController(AnalysisService analysisService, EmployeeService employeeService, Gson gson, ErrorMessage errorMessage) {
        this.analysisService = analysisService;
        this.employeeService = employeeService;
        this.gson = gson;
        this.errorMessage = errorMessage;
    }

    @GetMapping(value = "/analyses/add", produces = "application/json")
    public ResponseEntity<?> getAddAnalyses(){
        List<String> allEmployeeNames = this.employeeService.getAllEmployeeByUsername();
        return new ResponseEntity<>(this.gson.toJson(allEmployeeNames), HttpStatus.OK);
    }

    @PostMapping(value = "/analyses/add", produces = "application/json")
    public ResponseEntity<?> addAnalyses(@RequestBody AnalysisCreatedBindingModel addAnalysisBindingModel) {

        if(this.analysisService.analysesExist(addAnalysisBindingModel.getName())){
            this.errorMessage.setMessage("Analyses already exist");
            return new ResponseEntity<>(this.errorMessage, HttpStatus.BAD_REQUEST);
        }

        if (this.analysisService.save(addAnalysisBindingModel, this.employeeService)) {
            return new ResponseEntity<>("Successfully create analysis.", HttpStatus.OK);
        }
        this.errorMessage.setMessage("Something went wrong while processing your request...");

        return new ResponseEntity<>(this.errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);

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
            this.errorMessage.setMessage("Analyses doesn't exist");
            return new ResponseEntity<>(this.errorMessage, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(model, HttpStatus.OK);
    }

}
