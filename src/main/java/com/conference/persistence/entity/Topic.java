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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;

        Topic topic1 = (Topic) o;

        if (getIdSpeaker() != topic1.getIdSpeaker()) return false;
        if (getIdModer() != topic1.getIdModer()) return false;
        if (getId() != null ? !getId().equals(topic1.getId()) : topic1.getId() != null) return false;
        if (getTopic() != null ? !getTopic().equals(topic1.getTopic()) : topic1.getTopic() != null) return false;
        return getStatus() != null ? getStatus().equals(topic1.getStatus()) : topic1.getStatus() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTopic() != null ? getTopic().hashCode() : 0);
        result = 31 * result + (int) (getIdSpeaker() ^ (getIdSpeaker() >>> 32));
        result = 31 * result + (int) (getIdModer() ^ (getIdModer() >>> 32));
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        return result;
    }
}
