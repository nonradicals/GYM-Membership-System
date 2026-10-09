package com.gym.model;

import java.util.ArrayList;
import java.util.List;

public class Trainer extends Person {
    private final List<String> availability = new ArrayList<>();
    private final List<Session> assignedSessions = new ArrayList<>();

    public Trainer(String id, String name, String email, String contactNumber) {
        super(id, name, email, contactNumber);
    }

    public List<String> getAvailability() { return availability; }
    public void setAvailability(List<String> slots) {
        availability.clear();
        availability.addAll(slots);
    }

    public List<Session> getAssignedSessions() { return assignedSessions; }
    public void assignSession(Session session) { assignedSessions.add(session); }

    @Override
    public String getRole() { return "Trainer"; }
}
