package com.conference.persistence.entity;

import com.conference.persistence.idao.Identified;

import java.io.Serializable;

/**
 * Created by gleb on 11.12.17.
 */
public class User implements Serializable, Identified<Long> {
    private Long id;
    private String login;
    private String password;
    private String email;
    private String name;
    private String lastName;
    private long idUserType;

    public Long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getIdUserType() {
        return idUserType;
    }

    public void setIdUserType(long idUserType) {
        this.idUserType = idUserType;
    }
}
