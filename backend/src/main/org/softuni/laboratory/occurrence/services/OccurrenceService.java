package org.softuni.laboratory.occurrence.services;

import org.softuni.laboratory.employee.services.EmployeeService;
import org.softuni.laboratory.occurrence.models.binding.CreateOccurrenceBindingModel;
import org.softuni.laboratory.occurrence.models.binding.OccurrenceViewModel;
import org.softuni.laboratory.occurrence.models.entities.Occurrence;
import org.softuni.laboratory.patient.services.PatientService;

public interface OccurrenceService {

    OccurrenceViewModel save(CreateOccurrenceBindingModel occurrenceBindingModel, EmployeeService employeeService, PatientService patientService);
}
