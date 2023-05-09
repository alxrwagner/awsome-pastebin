package com.example.awsomepastebin.enums;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public enum ExpiryDate {
    TEN_MINUTES(Instant.now().plus(10, ChronoUnit.MINUTES)),
    ONE_HOURS(Instant.now().plus(1, ChronoUnit.HOURS)),
    THREE_HOURS(Instant.now().plus(3, ChronoUnit.HOURS)),
    ONE_DAY(Instant.now().plus(1, ChronoUnit.DAYS)),
    ONE_WEEK(Instant.now().plus(7, ChronoUnit.DAYS)),
    ONE_MONTH(Instant.now().plus(30, ChronoUnit.DAYS)),
    UNLIMITED(null);

    private final Instant date;

    ExpiryDate(Instant expiryDate) {
        this.date = expiryDate;
    }

    public Instant getDate() {
        return date;
    }
}
