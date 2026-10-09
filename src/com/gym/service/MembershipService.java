package com.gym.service;

import com.gym.model.Member;
import com.gym.model.MembershipPlan;
import com.gym.model.MembershipStatus;
import com.gym.model.Payment;

import java.time.LocalDate;
import java.util.UUID;

public class MembershipService {

    public void registerMember(Member member, MembershipPlan plan) {
        member.setMembershipPlan(plan);
        member.setStatus(MembershipStatus.EXPIRED);
    }

    public Payment processPayment(Member member, double amount) {
        Payment payment = new Payment(UUID.randomUUID().toString(), amount, LocalDate.now());
        payment.process();
        member.addPayment(payment);
        activateMembership(member);
        return payment;
    }

    private void activateMembership(Member member) {
        MembershipPlan plan = member.getMembershipPlan();
        plan.activate(LocalDate.now());
        member.setStatus(MembershipStatus.ACTIVE);
    }

    public boolean validateMembership(Member member) {
        if (member.hasActiveMembership()) {
            return true;
        }
        member.setStatus(MembershipStatus.EXPIRED);
        return false;
    }

    public void renewPlan(Member member, double amount, int extraMonths) {
        processPayment(member, amount);
        member.getMembershipPlan().extend(extraMonths);
        member.setStatus(MembershipStatus.ACTIVE);
    }
}
