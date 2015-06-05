package com.campusconnect.neo4j.types.web;

public class Reminder {

    private long createdDate;
    private String description;
    private long lastModifiedTime;
    private Long nodeId;

    private String reminderMessage;

    private long reminderTime;

    private String subject;

    public Reminder() {
    }

    public Reminder(long createdDate, String description, long lastModifiedTime, Long nodeId, String reminderMessage, long reminderTime, String subject) {
        this.createdDate = createdDate;
        this.description = description;
        this.lastModifiedTime = lastModifiedTime;
        this.nodeId = nodeId;
        this.reminderMessage = reminderMessage;
        this.reminderTime = reminderTime;
        this.subject = subject;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getReminderMessage() {
        return reminderMessage;
    }

    public void setReminderMessage(String reminderMessage) {
        this.reminderMessage = reminderMessage;
    }

    public long getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(long reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}