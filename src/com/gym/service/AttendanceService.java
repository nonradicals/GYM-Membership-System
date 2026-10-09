package com.gym.service;

import com.gym.model.AttendanceRecord;
import com.gym.model.Member;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class AttendanceService {

    public AttendanceRecord recordAttendance(Member member) {
        AttendanceRecord record = new AttendanceRecord(UUID.randomUUID().toString(), member, LocalDateTime.now());
        member.addAttendanceRecord(record);
        return record;
    }

    public List<AttendanceRecord> getAttendanceLogs(Member member) {
        return member.getAttendanceRecords();
    }

    public String generateReport(List<Member> members) {
        return members.stream()
                .map(m -> m.getName() + ": " + m.getAttendanceRecords().size() + " visit(s)")
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
