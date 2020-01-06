package io.github.komyagin.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

@ApiModel(description = "All details about the Notice")
public class Notice {

    @ApiModelProperty(notes = "The database generated notice ID")
    private int id;
    @ApiModelProperty(notes = "The peson who added notice to database")
    private int personId;
    @ApiModelProperty(notes = "The notice header")
    private String header;
    @ApiModelProperty(notes = "The notice body")
    private String body;
    @ApiModelProperty(notes = "The notice telephone number")
    private String telNumber;
    @ApiModelProperty(notes = "The notice created date generated by database")
    private LocalDateTime createdLocalDateTime;
    @ApiModelProperty(notes = "The notice updated date generated by database")
    private LocalDateTime updatedLocalDateTime;
    @ApiModelProperty(notes = "Category. Individual or Company")
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
