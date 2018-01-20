package com.conference.persistence.entity;

import com.conference.persistence.idao.Identified;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by gleb on 20.12.17.
 */
public class Report implements Serializable, Identified<Long> {
    private Long id;
    private String topic;
    private String place;
    private Calendar calendar = new GregorianCalendar();
    private long idSpeaker;
    private long idEvent;
    private String speakerLastName;

    @Override
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
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

    public long getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(long idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public String getSpeakerLastName() {
        return speakerLastName;
    }

    public void setSpeakerLastName(String speakerLastName) {
        this.speakerLastName = speakerLastName;
    }
}
