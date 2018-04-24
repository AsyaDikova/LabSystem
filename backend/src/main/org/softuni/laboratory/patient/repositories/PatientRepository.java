package org.softuni.laboratory.patient.repositories;

import org.softuni.laboratory.patient.models.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    Patient findByEmail(String email);
}
