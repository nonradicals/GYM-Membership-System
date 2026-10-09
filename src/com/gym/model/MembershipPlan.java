package com.gym.model;

import java.time.LocalDate;

public class MembershipPlan {
    private final String planId;
    private final String planName;
    private final int durationMonths;
    private final double price;
    private LocalDate startDate;
    private LocalDate endDate;

    public MembershipPlan(String planId, String planName, int durationMonths, double price) {
        this.planId = planId;
        this.planName = planName;
        this.durationMonths = durationMonths;
        this.price = price;
    }

    public void activate(LocalDate activationDate) {
        this.startDate = activationDate;
        this.endDate = activationDate.plusMonths(durationMonths);
    }

    public void extend(int months) {
        LocalDate base = (endDate == null) ? LocalDate.now() : endDate;
        this.endDate = base.plusMonths(months);
    }

    public boolean isActive() {
        return endDate != null && !LocalDate.now().isAfter(endDate);
    }

    public String getPlanId() { return planId; }
    public String getPlanName() { return planName; }
    public int getDurationMonths() { return durationMonths; }
    public double getPrice() { return price; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}
