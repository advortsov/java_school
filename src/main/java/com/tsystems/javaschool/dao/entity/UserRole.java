package com.tsystems.javaschool.dao.entity;

import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Alexander Dvortsov
 * @version 1.0
 * @since 19.02.2016
 * <p>
 * <p>
 * * create table users (
 * user_name         varchar(15) not null primary key,
 * user_pass         varchar(15) not null
 * );
 * <p>
 * create table user_roles (
 * user_name         varchar(15) not null,
 * role_name         varchar(15) not null,
 * primary key (user_name, role_name)
 * );
 */

@Entity
@IdClass(UserRoleId.class)
@Table(name = "user_roles")
public class UserRole {

    @Id
    @Column(name = "user_name", unique = true, length = 15, nullable = false)
    private String userName;

    @Id
    @Column(name = "role_name", unique = true, length = 15, nullable = false)
    private String userRole;


    public UserRole() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
