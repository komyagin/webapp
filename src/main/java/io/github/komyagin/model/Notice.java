package io.github.komyagin.model;

import java.time.LocalDateTime;

public class Notice {

    private static int id;
    private int personId;
    private String header;
    private String body;
    private String telNumber;
    private LocalDateTime createdLocalDateTime;
    private LocalDateTime updatedLocalDateTime;

    public Notice(int personId, String header, String body, String telNumber) {
        this.personId = personId;
        this.header = header;
        this.body = body;
        this.telNumber = telNumber;
    }
}
