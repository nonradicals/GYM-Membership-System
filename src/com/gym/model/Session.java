package com.gym.model;

import java.time.LocalDateTime;

public class Session {
    private final String sessionId;
    private final Member member;
    private final Trainer trainer;
    private final LocalDateTime dateTime;
    private SessionStatus status;

    public Session(String sessionId, Member member, Trainer trainer, LocalDateTime dateTime) {
        this.sessionId = sessionId;
        this.member = member;
        this.trainer = trainer;
        this.dateTime = dateTime;
        this.status = SessionStatus.REQUESTED;
    }

    public void confirm() { this.status = SessionStatus.CONFIRMED; }
    public void cancel() { this.status = SessionStatus.CANCELLED; }

    public String getSessionId() { return sessionId; }
    public Member getMember() { return member; }
    public Trainer getTrainer() { return trainer; }
    public LocalDateTime getDateTime() { return dateTime; }
    public SessionStatus getStatus() { return status; }
}
