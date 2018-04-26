package org.softuni.laboratory.occurrence.models.binding;

import org.softuni.laboratory.analysis.models.entities.Analysis;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

public class CreateOccurrenceBindingModel {

    @NotNull
    private String emailPatient;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @NotNull
    @Size(min = 9, max = 16)
    private int hours;

    @NotNull
    private String usernameEmployee;

    @NotNull
    private boolean isConsultation;

    @NotNull
    private String analysesName;

    public CreateOccurrenceBindingModel() {
    }

    public String getEmailPatient() {
        return emailPatient;
    }

    public void setEmailPatient(String emailPatient) {
        this.emailPatient = emailPatient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getUsernameEmployee() {
        return usernameEmployee;
    }

    public void setUsernameEmployee(String usernameEmployee) {
        this.usernameEmployee = usernameEmployee;
    }

    public boolean isConsultation() {
        return isConsultation;
    }

    public void setConsultation(boolean consultation) {
        isConsultation = consultation;
    }


    public String getAnalysesName() {
        return analysesName;
    }

    public void setAnalysesName(String analysesName) {
        this.analysesName = analysesName;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }
}
