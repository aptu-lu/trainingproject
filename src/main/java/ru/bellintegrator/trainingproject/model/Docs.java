package ru.bellintegrator.trainingproject.model;

import javax.persistence.*;

@Entity
@Table(name = "Docs")
public class Docs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Version
    private int version;

    @Column(name = "code", length = 50, nullable = false)
    private String dode;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDode() {
        return dode;
    }

    public void setDode(String dode) {
        this.dode = dode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
