package ru.job4j.job4j_passport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@NamedQuery(name = "findAll", query = "from Passport")
@NamedQuery(name = "findBySeries", query = "from Passport where series =: series")
@NamedQuery(name = "findUnavailable", query = "from Passport where finished <: date")
@NamedQuery(name = "deleteById", query = "delete Passport where id =: id")
@Entity
@Table(name = "passport", uniqueConstraints = {@UniqueConstraint(columnNames = {"series", "surname"})})
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Поле ИМЯ должно быть заполнено")
    private String name;

    @Column(name = "surname", nullable = false)
    @NotBlank(message = "Поле ФАМИЛИЯ должно быть заполнено")
    private String surname;

    @Column(name = "middle_name", nullable = false)
    @NotBlank(message = "Поле ОТЧЕСТВО должно быть заполнено")
    private String middleName;

    @Column(name = "series", nullable = false)
    private int series;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "created", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date created;

    @Column(name = "finished", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date finished;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public int getSeries() {
        return series;
    }

    public int getNumber() {
        return number;
    }

    public Date getCreated() {
        return created;
    }

    public Date getFinished() {
        return finished;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public void setFinished(Date finished) {
        this.finished = finished;
    }
}
