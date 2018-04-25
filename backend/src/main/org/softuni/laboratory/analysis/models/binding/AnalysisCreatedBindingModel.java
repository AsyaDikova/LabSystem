package org.softuni.laboratory.analysis.models.binding;


public class AnalysisCreatedBindingModel {

    private String name;

    private Double price;

    private String description;

    public AnalysisCreatedBindingModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
