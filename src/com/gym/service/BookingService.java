package com.gym.service;

import com.gym.model.Member;
import com.gym.model.Session;
import com.gym.model.Trainer;

import java.time.LocalDateTime;
import java.util.UUID;

public class BookingService {

    public boolean checkSlotAvailability(Trainer trainer, String slot) {
        return trainer.getAvailability().contains(slot);
    }

    public Session requestBooking(Member member, Trainer trainer, String slot, LocalDateTime dateTime) {
        if (!checkSlotAvailability(trainer, slot)) {
            return null;
        }
        Session session = new Session(UUID.randomUUID().toString(), member, trainer, dateTime);
        trainer.assignSession(session);
        trainer.getAvailability().remove(slot);
        return session;
    }

    public void confirmSession(Session session) {
        session.confirm();
    }
}
