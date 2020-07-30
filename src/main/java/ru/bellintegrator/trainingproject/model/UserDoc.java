package ru.bellintegrator.trainingproject.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Version;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.MapsId;
import java.time.LocalDate;

/**
 * Документы пользователя
 */
@Entity
@Table(name = "User_Doc")
public class UserDoc {

    /**
     * Уникальный идентификатор
     */
    @Id
    @Column(name = "user_id")
    private int id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private int version;

    /**
     * Дата
     */
    @Column(name = "doc_date", nullable = false)
    private LocalDate docDate;

    /**
     * Номер
     */
    @Column(name = "doc_number", length = 50, nullable = false)
    private String docNumber;

    /**
     * Пользователь
     */
    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    /**
     * Документы
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_id", nullable = false)
    private Docs docs;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
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
}
