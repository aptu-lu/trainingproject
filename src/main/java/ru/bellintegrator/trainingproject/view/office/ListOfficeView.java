package ru.bellintegrator.trainingproject.view.office;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ListOfficeView {

    @NotNull
    @Digits(integer = 10, fraction = 0)
    private int id;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    private Boolean isActive;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
