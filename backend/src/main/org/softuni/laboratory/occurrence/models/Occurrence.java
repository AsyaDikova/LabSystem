package org.softuni.laboratory.occurrence.models;

import org.hibernate.annotations.GenericGenerator;
import org.softuni.laboratory.employee.models.entities.Employee;
import org.softuni.laboratory.patient.models.entities.Patient;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name="date")
    private Date date;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="employee_id")
    private Employee employee;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    public Occurrence() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
