package ru.bellintegrator.trainingproject.filter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrganizationFilter {

    @NotNull(groups = MarkerValidate.Update.class)
    @Pattern(regexp = "^[0-9]+$", groups = MarkerValidate.Update.class)
    private Integer id;

    @NotNull(groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String name;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Size(min = 2, max = 50, groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String fullName;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String inn;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String kpp;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Size(min = 2, max = 50, groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String address;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$", groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String phone;

    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
