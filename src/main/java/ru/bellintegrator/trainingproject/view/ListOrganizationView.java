package ru.bellintegrator.trainingproject.view;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ListOrganizationView {

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    @Digits(integer = 15, fraction = 0)
    private String inn;

    private Boolean isActive;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
