package org.softuni.laboratory.patient.services;

import org.modelmapper.ModelMapper;
import org.softuni.laboratory.patient.models.binding.RegisterPatientBindingModel;
import org.softuni.laboratory.patient.models.entities.Patient;
import org.softuni.laboratory.patient.repositories.PatientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService{
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public boolean save(RegisterPatientBindingModel patientModel) {
        Patient patient = this.modelMapper.map(patientModel, Patient.class);

        this.patientRepository.save(patient);

        return true;
    }

    @Override
    public boolean patientExists(String email) {
        return this.patientRepository.findByEmail(email) != null;
    }

    @Override
    public Patient findByEmail(String email) {
        return this.patientRepository.findByEmail(email);
    }
}
