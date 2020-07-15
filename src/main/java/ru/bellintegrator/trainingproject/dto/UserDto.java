package ru.bellintegrator.trainingproject.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserDto {

    @NotNull(groups = {MarkerValidate.Get.class,MarkerValidate.Update.class})
    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.Get.class, MarkerValidate.Update.class})
    private int id;

    @NotNull(groups = {MarkerValidate.List.class,MarkerValidate.Save.class})
    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.List.class, MarkerValidate.Save.class})
    private int officeId;

    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String firstName;

    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String lastName;

    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String middleName;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String phone;

    @NotNull(groups = {MarkerValidate.Update.class,MarkerValidate.Save.class})
    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String position;

    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.List.class, MarkerValidate.Save.class})
    private String docCode;

    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String docNumber;

    @Past(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    private LocalDate docDate;

    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String citizenshipCode;

    private boolean isIdentified;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOfficeId() {
        return officeId;
    }

    public void setOfficeId(int officeId) {
        this.officeId = officeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }
}
