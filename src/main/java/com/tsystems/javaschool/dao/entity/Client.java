package com.tsystems.javaschool.dao.entity;


import javax.persistence.*;
import java.sql.Date;
import java.util.*;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 09.02.2016
 */
@Entity
@Table(name="client")
@NamedQuery(name = "Client.getAll", query = "SELECT b from Client b")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="birthday")
    private Date birthday;

    @Column(name="email")
    private String email;

    @Column(name="address")
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }


    public Client() {
    }


    public Client(String name, String surname, Date birthday, String email,
                  String address, User user, List<Order> orders) {
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
        this.user = user;
        this.orders = orders;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", user=" + user +
                ", orders=" + orders +
                '}';
    }


    //eq hashcode

}
