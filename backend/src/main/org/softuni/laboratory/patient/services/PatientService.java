package org.softuni.laboratory.patient.services;

import org.softuni.laboratory.patient.models.binding.RegisterPatientBindingModel;

public interface PatientService {
    boolean save(RegisterPatientBindingModel patientModel);

    boolean patientExists(String email);
}
