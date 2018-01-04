package com.conference.persistence.entity;

import com.conference.persistence.idao.Identified;

import java.io.Serializable;

/**
 * Created by gleb on 26.12.17.
 */
public class Salary implements Serializable, Identified<Long> {
    private Long id;
    private int rating;
    private double bonus;
    private long userId;

    @Override
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
