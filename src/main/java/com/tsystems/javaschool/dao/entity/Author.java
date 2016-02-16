package com.tsystems.javaschool.dao.entity;


import javax.persistence.*;

import java.util.Collection;
import java.util.Date;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 *
 *
 * POJO
 *
 */

@Entity
@Table(name="author")
//@NamedQuery(name = "Author.getById", query = "SELECT c from Car c")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;//

    @Column(name="fio")
    private String fio;

    public Author() {
    }

    public Author(String fio) {
        this.fio = fio;
    }

    public long getId() {
        return id;
    }


    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    @Override
    public String toString() {
        return fio;
    }
}
