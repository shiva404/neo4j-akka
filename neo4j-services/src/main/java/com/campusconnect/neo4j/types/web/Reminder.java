package com.campusconnect.neo4j.types.web;

public class Reminder {

    private Long createdDate;
    private String description;
    private Long lastModifiedTime;
    private Long nodeId;

    private String reminderMessage;

    private Long reminderTime;

    private String subject;

    public Reminder() {
    }

    public Reminder(Long createdDate, String description, Long lastModifiedTime, Long nodeId, String reminderMessage, Long reminderTime, String subject) {
        this.createdDate = createdDate;
        this.description = description;
        this.lastModifiedTime = lastModifiedTime;
        this.nodeId = nodeId;
        this.reminderMessage = reminderMessage;
        this.reminderTime = reminderTime;
        this.subject = subject;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Long lastModifiedTime) {
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

    public Long getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Long reminderTime) {
        this.reminderTime = reminderTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}