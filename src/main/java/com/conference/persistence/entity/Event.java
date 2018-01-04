package com.conference.persistence.entity;

import com.conference.persistence.idao.Identified;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Created by gleb on 11.12.17.
 */
public class Event implements Serializable, Identified<Long> {
    private Long id;
    private String topic;
    private String place;
    private Calendar calendar = new GregorianCalendar();

    public Long getId() {
        return id;
    }

    protected void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", place='" + place + '\'' +
                ", calendar=" + calendar.getTime() +
                '}';
    }
}
