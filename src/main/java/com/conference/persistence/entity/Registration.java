package com.conference.persistence.entity;

import com.conference.persistence.idao.Identified;

import java.io.Serializable;

/**
 * Created by gleb on 26.12.17.
 */
public class Registration implements Serializable, Identified<Long> {
    private Long id;
    private long userId;
    private long eventId;
    private long reportId;

    @Override
    public Long getId() {
        return id;
    }

    protected void setId(Long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getEventId() {
        return eventId;
    }

    public void setEventId(long eventId) {
        this.eventId = eventId;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }
}
