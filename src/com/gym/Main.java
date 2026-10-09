package com.gym;

import com.gym.model.Admin;
import com.gym.model.Member;
import com.gym.model.MembershipPlan;
import com.gym.model.Session;
import com.gym.model.Trainer;
import com.gym.service.AttendanceService;
import com.gym.service.BookingService;
import com.gym.service.MembershipService;

import java.time.LocalDateTime;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MembershipService membershipService = new MembershipService();
        BookingService bookingService = new BookingService();
        AttendanceService attendanceService = new AttendanceService();

        Member member = new Member("M001", "Juan Dela Cruz", "juan@example.com", "0917-000-0000");
        Trainer trainer = new Trainer("T001", "Maria Santos", "maria@example.com", "0917-111-1111");
        trainer.setAvailability(List.of("MON-6AM", "WED-6AM", "FRI-6AM"));

        MembershipPlan plan = new MembershipPlan("P001", "Monthly Plan", 1, 1200.0);

        // Sign-up -> payment -> membership activation
        membershipService.registerMember(member, plan);
        membershipService.processPayment(member, plan.getPrice());
        System.out.println(member.getName() + " membership active: " + membershipService.validateMembership(member));

        // Attendance logging on gym access
        attendanceService.recordAttendance(member);

        // Booking a trainer session
        Session session = bookingService.requestBooking(member, trainer, "MON-6AM", LocalDateTime.now().plusDays(1));
        if (session != null) {
            bookingService.confirmSession(session);
            System.out.println("Session booked with " + trainer.getName() + " - status: " + session.getStatus());
        }

        // Admin reporting
        Admin admin = new Admin("A001", "Admin User", "admin@example.com", "0917-222-2222");
        System.out.println(admin.getName() + " report:");
        System.out.println(attendanceService.generateReport(List.of(member)));
    }
}
