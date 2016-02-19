package com.tsystems.javaschool.dao.entity;


import javax.persistence.*;
import java.util.List;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */

@Entity
@Table(name="genre")//
@NamedQuery(name = "Genre.getAll", query = "SELECT g from Genre g")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", unique = true)
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
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
