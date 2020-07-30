package ru.bellintegrator.trainingproject.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Пользователь
 */
@Entity
@Table(name = "User")
public class User {

    /**
     * Уникальный идентификатор
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private int version;

    /**
     * Имя
     */
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    /**
     * Фамилия
     */
    @Column(name = "last_name", length = 50)
    private String lastName;

    /**
     * Отчество
     */
    @Column(name = "middle_name", length = 50)
    private String middleName;

    /**
     * Должность
     */
    @Column(name = "position", length = 50, nullable = false)
    private String position;

    /**
     * Телефон
     */
    @Column(name = "phone", length = 50)
    private String phone;

    /**
     * Идентифицирован ли
     */
    @Column(name = "is_identified")
    private Boolean isIdentified;

    /**
     * Офис
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "office_id")
    private Office office;

    /**
     * Документы пользователя
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserDoc userDoc;

    /**
     * Страны
     */
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "citizenship_id")
    private Countries countries;

    public int getId() {
        return id;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
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

    public UserDoc getUserDoc() {
        return userDoc;
    }

    public void setUserDoc(UserDoc userDoc) {
        this.userDoc = userDoc;
    }

    public Countries getCountries() {
        return countries;
    }

    public void setCountries(Countries countries) {
        this.countries = countries;
    }

    public Boolean getIdentified() {
        return isIdentified;
    }

    public void setIdentified(Boolean identified) {
        isIdentified = identified;
    }
}
