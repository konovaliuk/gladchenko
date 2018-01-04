package com.conference.persistence.entity;

import com.conference.persistence.idao.Identified;

import java.io.Serializable;

/**
 * Created by gleb on 27.12.17.
 */
public class Topic implements Serializable, Identified<Long> {
    private Long id;
    private String topic;
    private long idSpeaker;
    private long idModer;
    private String status;

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

    public long getIdSpeaker() {
        return idSpeaker;
    }

    public void setIdSpeaker(long idSpeaker) {
        this.idSpeaker = idSpeaker;
    }

    public long getIdModer() {
        return idModer;
    }

    public void setIdModer(long idModer) {
        this.idModer = idModer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
