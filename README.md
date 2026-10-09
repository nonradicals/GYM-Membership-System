# Gym Membership System

## Project Description
This is my capstone project, a Gym Membership System based on gyms like Anytime Fitness. Right now most small gyms handle memberships, payments, and attendance manually, through spreadsheets, paper logs, or just remembering who paid. That gets messy fast and makes it hard to tell who's actually allowed in the gym or who still owes a renewal. This system puts membership, payments, gym access, attendance, and trainer bookings in one place instead.

Who uses it:
- **Members** sign up, pay, renew when their plan expires, get access to the gym, and book sessions with a trainer.
- **Trainers** set their available time slots, confirm bookings, and check attendance for their own clients.
- **Admins** keep an eye on attendance across all members and pull reports.

## Objectives
- Let members register for a plan, pay, and renew it.
- Check membership status automatically before letting someone into the gym.
- Let members book sessions with a trainer based on real availability.
- Keep a record of member attendance over time.
- Give admins a way to see attendance data and generate reports.

## Key Features
- Membership registration and payment, which activates the plan.
- Membership validation on gym entry, with renewal if it's expired.
- Attendance logging every time a member gets gym access.
- Session booking between members and trainers, with trainer confirmation.
- Attendance reports for admins, and per-client attendance views for trainers.

## Technology Stack
- Java, plain OOP, no frameworks.
- JDK (`javac` / `java`) to build and run, no build tool needed.
- Diagrams done in Mermaid (Use Case, Class) and draw.io (Sequence, Activity).

## Project Structure
```
GYM Membership System/
├── README.md
├── .gitignore
├── src/                        # Source code
│   └── com/gym/
│       ├── Main.java           # Demo entry point wiring the services together
│       ├── model/              # Domain classes (Member, Trainer, Admin, MembershipPlan, Session, Payment, AttendanceRecord)
│       └── service/            # Business logic (MembershipService, BookingService, AttendanceService)
└── docs/                       # Documentation
    ├── project-brief.png       # Original assignment notes for this project
    └── uml/                    # UML diagram files
        ├── use-case-diagram.mmd
        ├── class-diagram.mmd
        ├── sequence-diagram.png
        └── activity-diagram.png
```

## Setup / Execution Instructions
Just needs a JDK (11+) installed, no build tool.

```bash
# From the project root

# 1. Compile everything into a bin/ folder
javac -d bin $(find src -name "*.java")

# 2. Run it
java -cp bin com.gym.Main
```

On Windows PowerShell, step 1 looks like this instead:
```powershell
javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
```

`Main.java` just runs through the main flow as a demo: a member signs up, pays, gets their membership activated, has a gym visit logged as attendance, books a session with a trainer, and an admin pulls an attendance report.

## UML Diagrams

### Use Case Diagram
Actors are Member, Trainer, and Admin. Covers registering a membership, paying, renewing, getting gym access, booking a session, setting availability, confirming a session, and tracking/monitoring attendance plus reports.

```mermaid
flowchart LR
    Member([Member])
    Trainer([Trainer])
    Admin([Admin])

    subgraph System["Gym Membership System"]
        UC1(["Register Membership"])
        UC2(["Make Payment"])
        UC3(["Renew Membership Plan"])
        UC4(["Request Gym Access"])
        UC5(["Book Training Session"])
        UC6(["Set Availability"])
        UC7(["Confirm Session"])
        UC8(["View Client Attendance"])
        UC9(["Track Attendance"])
        UC10(["Monitor Attendance Logs"])
        UC11(["Generate Reports"])
    end

    Member --> UC1
    Member --> UC3
    Member --> UC4
    Member --> UC5
    Member --> UC9

    Trainer --> UC6
    Trainer --> UC7
    Trainer --> UC8

    Admin --> UC10
    Admin --> UC11

    UC1 -. includes .-> UC2
    UC3 -. includes .-> UC2
    UC5 -. includes .-> UC7
```
Source file: [docs/uml/use-case-diagram.mmd](docs/uml/use-case-diagram.mmd)

### Class Diagram
`Member`, `Trainer`, and `Admin` all extend an abstract `Person` class. Around that are `MembershipPlan`, `Session`, `Payment`, and `AttendanceRecord`, plus the service classes (`MembershipService`, `BookingService`, `AttendanceService`) that hold the actual logic.

```mermaid
classDiagram
    class Person {
        <<abstract>>
        -String id
        -String name
        -String email
        -String contactNumber
        +getId() String
        +getName() String
        +getEmail() String
        +getContactNumber() String
        +getRole()* String
    }

    class Member {
        -MembershipPlan membershipPlan
        -MembershipStatus status
        -List~Payment~ paymentHistory
        -List~AttendanceRecord~ attendanceRecords
        +hasActiveMembership() boolean
        +addPayment(payment) void
        +addAttendanceRecord(record) void
        +getRole() String
    }

    class Trainer {
        -List~String~ availability
        -List~Session~ assignedSessions
        +setAvailability(slots) void
        +assignSession(session) void
        +getRole() String
    }

    class Admin {
        +getRole() String
    }

    class MembershipPlan {
        -String planId
        -String planName
        -int durationMonths
        -double price
        -LocalDate startDate
        -LocalDate endDate
        +activate(date) void
        +extend(months) void
        +isActive() boolean
    }

    class Session {
        -String sessionId
        -LocalDateTime dateTime
        -SessionStatus status
        +confirm() void
        +cancel() void
    }

    class Payment {
        -String paymentId
        -double amount
        -LocalDate date
        -PaymentStatus status
        +process() boolean
    }

    class AttendanceRecord {
        -String recordId
        -LocalDateTime timestamp
    }

    class MembershipService {
        +registerMember(member, plan) void
        +processPayment(member, amount) Payment
        +validateMembership(member) boolean
        +renewPlan(member, amount, months) void
    }

    class BookingService {
        +checkSlotAvailability(trainer, slot) boolean
        +requestBooking(member, trainer, slot, dateTime) Session
        +confirmSession(session) void
    }

    class AttendanceService {
        +recordAttendance(member) AttendanceRecord
        +getAttendanceLogs(member) List~AttendanceRecord~
        +generateReport(members) String
    }

    Person <|-- Member
    Person <|-- Trainer
    Person <|-- Admin

    Member "1" --> "0..1" MembershipPlan : has
    Member "1" --> "*" Payment : makes
    Member "1" --> "*" AttendanceRecord : logs
    Member "1" --> "*" Session : books
    Trainer "1" --> "*" Session : conducts

    MembershipService ..> Member : manages
    MembershipService ..> Payment : creates
    BookingService ..> Session : creates
    AttendanceService ..> AttendanceRecord : creates
```
Source file: [docs/uml/class-diagram.mmd](docs/uml/class-diagram.mmd)

### Sequence Diagram
Walks through the main flow: sign-up, payment, access validation, attendance logging, session booking, and reporting.

![Sequence Diagram](docs/uml/sequence-diagram.png)

### Activity Diagram
Shows the workflow across Member, System, Trainer, and Admin, including the decision point for granting/denying gym access and the renewal path when a membership has expired.

![Activity Diagram](docs/uml/activity-diagram.png)
