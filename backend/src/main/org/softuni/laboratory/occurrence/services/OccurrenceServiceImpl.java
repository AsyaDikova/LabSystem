package org.softuni.laboratory.occurrence.services;

import org.modelmapper.ModelMapper;
import org.softuni.laboratory.employee.models.entities.Employee;
import org.softuni.laboratory.employee.services.EmployeeService;
import org.softuni.laboratory.occurrence.models.binding.CreateOccurrenceBindingModel;
import org.softuni.laboratory.occurrence.models.entities.Occurrence;
import org.softuni.laboratory.occurrence.repositories.OccurrenceRepository;
import org.softuni.laboratory.patient.models.entities.Patient;
import org.softuni.laboratory.patient.services.PatientService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class OccurrenceServiceImpl implements OccurrenceService{

    private final OccurrenceRepository occurrenceRepository;

    private final ModelMapper modelMapper;

    public OccurrenceServiceImpl(OccurrenceRepository occurrenceRepository, ModelMapper modelMapper) {
        this.occurrenceRepository = occurrenceRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean save(CreateOccurrenceBindingModel occurrenceBindingModel, EmployeeService employeeService, PatientService patientService) {
        Patient patient = patientService.findByEmail(occurrenceBindingModel.getEmailPatient());
        Employee employee = employeeService.findByUsername(occurrenceBindingModel.getUsernameEmployee());

        Occurrence occurrence = this.modelMapper.map(occurrenceBindingModel, Occurrence.class);
        occurrence.setEmployee(employee);
        occurrence.setPatient(patient);

        this.occurrenceRepository.save(occurrence);

        return true;
    }
}
