package org.softuni.laboratory.analysis.services;

import org.modelmapper.ModelMapper;
import org.softuni.laboratory.analysis.models.binding.AnalysisCreatedBindingModel;
import org.softuni.laboratory.analysis.models.binding.AnalyzesViewModel;
import org.softuni.laboratory.analysis.models.entities.Analysis;
import org.softuni.laboratory.analysis.repositories.AnalysisRepository;;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnalysisServiceImpl implements AnalysisService{
    private final ModelMapper modelMapper;

    private final AnalysisRepository analysisRepository;

    public AnalysisServiceImpl(ModelMapper modelMapper, AnalysisRepository analysisRepository) {
        this.modelMapper = modelMapper;
        this.analysisRepository = analysisRepository;
    }

    @Override
    public boolean save(AnalysisCreatedBindingModel analysisModel, EmployeeService employeeService) {
        Analysis analysis = this.modelMapper.map(analysisModel, Analysis.class);
        analysis.setEmployee(employeeService.findByUsername(analysisModel.getName()));
        this.analysisRepository.save(analysis);
        return true;
    }

    @Override
    public List<AnalyzesViewModel> getAllAnalyzes() {
        List<AnalyzesViewModel> allAnalyzes =
                this.analysisRepository.findAll()
                .stream()
                .map(x -> this.modelMapper.map(x, AnalyzesViewModel.class))
                .collect(Collectors.toList());
        return allAnalyzes;
    }

    @Override
    public AnalyzesViewModel getOneById(String id) {
        Analysis analysis = this.analysisRepository.findById(id).orElse(null);
        if(analysis == null){
            return null;
        }
        AnalyzesViewModel analyzesViewModel = this.modelMapper.map(analysis, AnalyzesViewModel.class);
        return analyzesViewModel;
    }

    @Override
    public boolean analysesExist(String name) {
        return this.analysisRepository.findByName(name)!= null;
    }

    @Override
    public List<String> getAllAnalyzesName() {
        List<Analysis> allAnalyzes = this.analysisRepository.findAll();
        List<String> analyzesName = allAnalyzes.stream().map(Analysis::getName).collect(Collectors.toList());
        return analyzesName;
    }
}
