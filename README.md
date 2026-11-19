# PROG2004 Assessment 1 — Appointment System

## Run

Open the extracted project folder in VS Code with the Java extensions installed, open `src/AssignmentOne.java`, and run its main method.

Alternatively, from the project directory in PowerShell:

```powershell
New-Item -ItemType Directory -Path bin -Force | Out-Null
javac -encoding UTF-8 -Xlint:all -d bin src/*.java
java -cp bin AssignmentOne
```

The course specifies JDK 26. This source has been compiled and exercised with JDK 17.0.14; JDK 26 has not been tested in this workspace.

## Model

- `HealthProfessional`: abstract parent with numeric identity, name and weekly working days; ID-based equals/hashCode.
- `GeneralPractitioner`, `Surgeon`, `Dietitian`, `Nurse`: four specialisations, each with its own information and service description.
- `Patient`: patient name and digits-only mobile phone, stored as text to preserve leading zeroes.
- `Appointment`: links a patient, professional and time; implements Comparable for chronological ordering.
- `AppointmentManager`: adds, filters, displays, sorts and cancels today's appointments; rejects professional/time conflicts.
- `DailySchedule`: central definition of legal appointment starts.
- `Validation`: shared text and digit checks.
- `AssignmentOne`: scripted demonstration of Parts 1–6, including success and failure scenarios.

Private fields, validated creation methods and copied arrays/collections protect object state. Invalid creation returns null with a warning; callers check the result. Shared behaviour is reused through inheritance, abstract operations define the common contract, and parent-typed collections and parameters support polymorphism.

## Business assumptions

1. The system represents today's in-memory appointments only. It accepts no booking date and stores no historical or future bookings.
2. Opening hours are 08:00–16:00. Each appointment lasts 30 minutes; the final permitted start is 15:30. The time must exactly match a schedule entry in HH:mm format.
3. Working days are stored, validated and displayed. The current weekday is not checked against these days.
4. Cancelling by professional ID plus time removes one booking. Cancelling by patient phone removes all matching bookings for today.
5. IDs are non-negative integers and identify professionals across separate Java objects. There is no separate professional registry.
6. The brief specifies no phone length or country format, so the system checks only that a phone is nonempty and contains ASCII digits.
7. Different professionals may have bookings at the same time. Only a professional/time collision is rejected.

The demonstration uses fictional people and finishes with five appointments.

