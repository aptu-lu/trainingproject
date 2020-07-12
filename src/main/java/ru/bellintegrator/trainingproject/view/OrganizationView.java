package ru.bellintegrator.trainingproject.view;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class OrganizationView {

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Get.class})
    @Digits(integer = 10, fraction = 0)
    private int id;

    @NotNull(groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Size(min = 2, max = 50)
    private String name;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Size(min = 2, max = 50)
    private String fullName;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Digits(integer = 15, fraction = 0)
    private String inn;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Digits(integer = 15, fraction = 0)
    private String kpp;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Size(min = 2, max = 50)
    private String address;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;

    private boolean isActive;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
