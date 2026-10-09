package com.gym.model;

import java.time.LocalDateTime;

public class AttendanceRecord {
    private final String recordId;
    private final Member member;
    private final LocalDateTime timestamp;

    public AttendanceRecord(String recordId, Member member, LocalDateTime timestamp) {
        this.recordId = recordId;
        this.member = member;
        this.timestamp = timestamp;
    }

    public String getRecordId() { return recordId; }
    public Member getMember() { return member; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
