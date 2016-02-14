package com.tsystems.javaschool.dao.entity;

import javax.persistence.*;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */

@Entity
@Table(name="publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "source_pkey")
    private long id;
    @Column(name="name")
    private String name;

    public Publisher() {
    }

    public Publisher(String name) {
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
