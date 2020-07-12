package ru.bellintegrator.trainingproject.view;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Size;

public class DocsView {

    @Digits(integer = 10, fraction = 0)
    private int id;

    @Digits(integer = 10, fraction = 0)
    private String code;

    @Size(min = 2, max = 50)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
