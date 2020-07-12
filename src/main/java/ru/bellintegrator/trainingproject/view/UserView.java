package ru.bellintegrator.trainingproject.view;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class UserView {

    @NotNull(groups = {MarkerValidate.Get.class,MarkerValidate.Update.class})
    @Digits(integer = 10, fraction = 0)
    private int id;

    @NotNull(groups = {MarkerValidate.List.class, MarkerValidate.Save.class})
    @Digits(integer = 10, fraction = 0)
    private int officeId;

    @NotNull(groups = {MarkerValidate.Update.class,MarkerValidate.Save.class})
    @Size(min = 2, max = 50)
    private String firstName;

    @Size(min = 2, max = 50)
    private String lastName;

    @Size(min = 2, max = 50)
    private String middleName;

    @NotNull(groups = {MarkerValidate.Update.class,MarkerValidate.Save.class})
    @Size(min = 2, max = 50)
    private String position;

    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$")
    private String phone;

    private boolean isIdentified;

    @Digits(integer = 10, fraction = 0)
    private String docCode;

    @Size(min = 2, max = 50)
    private String docName;

    @Past
    private LocalDate docDate;

    @Digits(integer = 10, fraction = 0)
    private String docNumber;

    @Digits(integer = 10, fraction = 0)
    private String citizenshipCode;

    @Size(min = 2, max = 50)
    private String citizenshipName;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isIdentified() {
        return isIdentified;
    }

    public void setIdentified(boolean identified) {
        isIdentified = identified;
    }

    public String getDocCode() {
        return docCode;
    }

    public void setDocCode(String docCode) {
        this.docCode = docCode;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public LocalDate getDocDate() {
        return docDate;
    }

    public void setDocDate(LocalDate docDate) {
        this.docDate = docDate;
    }

    public String getDocNumber() {
        return docNumber;
    }

    public void setDocNumber(String docNumber) {
        this.docNumber = docNumber;
    }

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public String getCitizenshipName() {
        return citizenshipName;
    }

    public void setCitizenshipName(String citizenshipName) {
        this.citizenshipName = citizenshipName;
    }
}
