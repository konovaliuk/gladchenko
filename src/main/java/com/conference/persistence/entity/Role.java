package com.conference.persistence.entity;

import com.conference.persistence.idao.Identified;

import java.io.Serializable;

/**
 * Created by gleb on 26.12.17.
 */
public class Role implements Serializable, Identified<Long> {
    private Long id;
    private String role;
    private String description;

    @Override
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
