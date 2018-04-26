package org.softuni.laboratory.analysis.services;


import org.softuni.laboratory.analysis.models.binding.AnalysisCreatedBindingModel;
import org.softuni.laboratory.analysis.models.binding.AnalyzesViewModel;
import org.softuni.laboratory.employee.services.EmployeeService;

import java.util.List;

public interface AnalysisService {

    boolean save(AnalysisCreatedBindingModel analysis, EmployeeService employeeService);

    List<AnalyzesViewModel> getAllAnalyzes();

    AnalyzesViewModel getOneById(String id);

    boolean analysesExist(String name);

    List<String> getAllAnalyzesName();
}
