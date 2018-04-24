package org.softuni.laboratory.patient.services;

import org.softuni.laboratory.patient.models.binding.RegisterPatientBindingModel;
import org.softuni.laboratory.patient.models.entities.Patient;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PatientService extends UserDetailsService {
    boolean save(RegisterPatientBindingModel patientModel);

    boolean patientExists(String email);
}
