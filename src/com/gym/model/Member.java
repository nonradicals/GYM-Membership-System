package com.gym.model;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person {
    private MembershipPlan membershipPlan;
    private MembershipStatus status;
    private final List<Payment> paymentHistory = new ArrayList<>();
    private final List<AttendanceRecord> attendanceRecords = new ArrayList<>();

    public Member(String id, String name, String email, String contactNumber) {
        super(id, name, email, contactNumber);
        this.status = MembershipStatus.EXPIRED;
    }

    public MembershipPlan getMembershipPlan() { return membershipPlan; }
    public void setMembershipPlan(MembershipPlan membershipPlan) { this.membershipPlan = membershipPlan; }

    public MembershipStatus getStatus() { return status; }
    public void setStatus(MembershipStatus status) { this.status = status; }

    public List<Payment> getPaymentHistory() { return paymentHistory; }
    public void addPayment(Payment payment) { paymentHistory.add(payment); }

    public List<AttendanceRecord> getAttendanceRecords() { return attendanceRecords; }
    public void addAttendanceRecord(AttendanceRecord record) { attendanceRecords.add(record); }

    public boolean hasActiveMembership() {
        return status == MembershipStatus.ACTIVE && membershipPlan != null && membershipPlan.isActive();
    }

    @Override
    public String getRole() { return "Member"; }
}
