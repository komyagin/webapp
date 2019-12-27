package io.github.komyagin.model;

import java.time.LocalDateTime;

public class Notice {

    private int id;
    private int personId;
    private String header;
    private String body;
    private String telNumber;
    private LocalDateTime createdLocalDateTime;
    private LocalDateTime updatedLocalDateTime;
    private Category category;

    public Notice(int id, int personId, String header, String body, String telNumber, LocalDateTime createdLocalDateTime,
                  LocalDateTime updatedLocalDateTime, Category category) {
        this.id = id;
        this.personId = personId;
        this.header = header;
        this.body = body;
        this.telNumber = telNumber;
        this.createdLocalDateTime = createdLocalDateTime;
        this.updatedLocalDateTime = updatedLocalDateTime;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public LocalDateTime getCreatedLocalDateTime() {
        return createdLocalDateTime;
    }

    public void setCreatedLocalDateTime(LocalDateTime createdLocalDateTime) {
        this.createdLocalDateTime = createdLocalDateTime;
    }

    public LocalDateTime getUpdatedLocalDateTime() {
        return updatedLocalDateTime;
    }

    public void setUpdatedLocalDateTime(LocalDateTime updatedLocalDateTime) {
        this.updatedLocalDateTime = updatedLocalDateTime;
    }

    public Category getCategory() {
        return category;
    }

}
