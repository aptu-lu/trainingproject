package ru.bellintegrator.trainingproject.view.office;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Представление офиса
 */
public class OfficeView {

    /**
     * Уникальный идентификатор
     */
    private int id;

    /**
     * Название
     */
    private String name;

    /**
     * Адресс
     */
    private String address;

    /**
     * Телефон
     */
    private String phone;

    /**
     * Действителен ли
     */
    @JsonProperty("isActive")
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

    @JsonProperty("isActive")
    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
