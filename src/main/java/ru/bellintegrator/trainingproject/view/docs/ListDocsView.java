package ru.bellintegrator.trainingproject.view.docs;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ListDocsView {

    @NotNull
    @Digits(integer = 10, fraction = 0)
    private String code;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

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
