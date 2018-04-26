package org.softuni.laboratory.patient.services;

import org.softuni.laboratory.patient.models.binding.RegisterPatientBindingModel;
import org.softuni.laboratory.patient.models.entities.Patient;

public interface PatientService {
    boolean save(RegisterPatientBindingModel patientModel);

    boolean patientExists(String email);

    Patient findByEmail(String email);
}
