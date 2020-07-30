package ru.bellintegrator.trainingproject.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

/**
 * Офис
 */
@Entity
@Table(name = "Office")
public class Office {

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
     * Название
     */
    @Column(name = "name", length = 50)
    private String name;

    /**
     * Адресс
     */
    @Column(name = "address", length = 50)
    private String address;

    /**
     * Телефон
     */
    @Column(name = "phone", length = 50)
    private String phone;

    /**
     * Действителен ли
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Организация
     */
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Boolean getActive() {
        return isActive;
    }
}
