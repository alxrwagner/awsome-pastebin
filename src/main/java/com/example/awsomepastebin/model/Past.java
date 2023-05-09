package com.example.awsomepastebin.model;

import com.example.awsomepastebin.enums.Status;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.Instant;

@Entity
@Data
public class Past {
    @Id
    private String id;
    private String title;
    private String body;
    private Instant expiryDate;
    @Enumerated(EnumType.STRING)
    private Status status;
    private Instant dateCreate;
}
