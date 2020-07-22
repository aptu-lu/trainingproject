package ru.bellintegrator.trainingproject.filter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * Фильтр User
 */
public class UserFilter {

    /**
     * Уникальный идентификатор
     */
    @NotNull(groups = MarkerValidate.Update.class)
    @Pattern(regexp = "^[0-9]+$", groups = MarkerValidate.Update.class)
    private Integer id;

    /**
     * Уникальный идентификатор офиса
     */
    @NotNull(groups = {MarkerValidate.List.class,MarkerValidate.Save.class})
    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.List.class, MarkerValidate.Save.class})
    private Integer officeId;

    /**
     * Имя
     */
    @NotNull(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String firstName;

    /**
     * Фамилия
     */
    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String lastName;

    /**
     * Отчество
     */
    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String middleName;

    /**
     * Телефон
     */
    @Pattern(regexp = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$",
            groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String phone;

    /**
     * Позиция
     */
    @NotNull(groups = {MarkerValidate.Update.class,MarkerValidate.Save.class})
    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String position;

    /**
     * Название документа
     */
    @Size(min = 2, max = 50, groups = {MarkerValidate.List.class, MarkerValidate.Save.class})
    private String docName;

    /**
     * Номер документа
     */
    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String docNumber;

    /**
     * Дата документа
     */
    @Past(groups = {MarkerValidate.Update.class, MarkerValidate.Save.class})
    private LocalDate docDate;

    /**
     * Код страны
     */
    @Pattern(regexp = "^[0-9]+$", groups = {MarkerValidate.List.class, MarkerValidate.Update.class, MarkerValidate.Save.class})
    private String citizenshipCode;

    /**
     * Идентифицирован ли
     */
    private Boolean isIdentified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
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

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
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

    public String getCitizenshipCode() {
        return citizenshipCode;
    }

    public void setCitizenshipCode(String citizenshipCode) {
        this.citizenshipCode = citizenshipCode;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
