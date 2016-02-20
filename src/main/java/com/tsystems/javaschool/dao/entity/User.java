package com.tsystems.javaschool.dao.entity;

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
    @Column(name="user_name", length = 15, unique = true, nullable = false)
    private String userName;

    @Column(name="user_pass", length = 15, nullable = false)
    private String userPass;

    //    @OneToOne(cascade = CascadeType.DETACH)
//    @JoinColumn(name="genre_id") // this column inda book table
//    private Genre genre;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String user_name) {
        this.userName = user_name;
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
                "user_name='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                '}';
    }
}
