package ru.bellintegrator.trainingproject.view.organization;

/**
 * Представление списка организации
 */
public class ListOrganizationView {

    /**
     * Уникальный идентификатор
     */
    private int id;

    /**
     * Название
     */
    private String name;

    /**
     * Действителен ли
     */
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

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
