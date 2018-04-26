package org.softuni.laboratory.occurrence.models.entities;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.laboratory.employee.models.entities.Employee;
import org.softuni.laboratory.patient.models.entities.Patient;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "occurrences")
public class Occurrence {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )

    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @Column(name = "date", nullable = false)
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    @Column(name = "hours", nullable = false)
    private int hours;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    @Column(name = "analyses_name")
    private String analysesName;

    @Column
    private boolean isConsultation;

    public Occurrence() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getAnalysesName() {
        return this.analysesName;
    }

    public void setAnalysesName(String analysesName) {
        this.analysesName = analysesName;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isConsultation() {
        return isConsultation;
    }

    public void setConsultation(boolean consultation) {
        isConsultation = consultation;
    }
}
