package com.tsystems.javaschool.dao.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 19.02.2016
 *
 *
 *
 create table users (
user_name         varchar(15) not null primary key,
user_pass         varchar(15) not null
);

create table user_roles (
user_name         varchar(15) not null,
role_name         varchar(15) not null,
primary key (user_name, role_name)
);
 */

@Entity
@Table(name="users")
public class User implements Serializable{

//    @Id
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name="client_id")
//    private Client client;
    @Id
    @Column(name="user_name", length = 15)
    @NotNull
    private String user_name;

    @Column(name="user_pass", length = 15)
    @NotNull
    private String userPass;

    //    @OneToOne(cascade = CascadeType.DETACH)
//    @JoinColumn(name="genre_id") // this column inda book table
//    private Genre genre;


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_name='" + user_name + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
