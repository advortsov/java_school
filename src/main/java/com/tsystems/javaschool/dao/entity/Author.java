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

    @Column(name="name", unique = true, nullable = false)
    // from 20 feb //@Column(nulable = false)
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
