package ru.bellintegrator.trainingproject.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Version;

/**
 * Документ
 */
@Entity
@Table(name = "Docs")
public class Docs {

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
     * Код
     */
    @Column(name = "code", length = 50, nullable = false)
    private String code;

    /**
     * Название
     */
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public int getId() {
        return id;
    }

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
