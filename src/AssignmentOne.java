import java.util.ArrayList;

/** Scripted demonstration covering all six parts of Assessment 1. */
public class AssignmentOne {
    /** Creates staff and exercises successful and unsuccessful booking operations. */
    public static void main(String[] args) {
        String[] weekdays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        String[] selectedDays = {"Monday", "Wednesday", "Friday"};
        GeneralPractitioner gpA = GeneralPractitioner.create(101, "Dr Alex Chen", weekdays);
        GeneralPractitioner gpB = GeneralPractitioner.create(102, "Dr Maya Patel", weekdays, "Preventive care");
        GeneralPractitioner gpC = GeneralPractitioner.create(103, "Dr Sam Lee", selectedDays, "Older adult health");
        Surgeon surgeon = Surgeon.create(201, "Dr Olivia Brown", weekdays, "Orthopaedics");
        Dietitian dietitian = Dietitian.create(301, "Jamie Wilson", selectedDays, "Diabetes management");
        Nurse nurse = Nurse.create(401, "Taylor Nguyen", weekdays, "blood sample collection");

        System.out.println("=== PARTS 1-3: HEALTH PROFESSIONALS ===");
        ArrayList<HealthProfessional> staff = new ArrayList<HealthProfessional>();
        staff.add(gpA);
        staff.add(gpB);
        staff.add(gpC);
        staff.add(surgeon);
        staff.add(dietitian);
        staff.add(nurse);
        for (HealthProfessional professional : staff) {
            System.out.println(professional);
            System.out.println("  Service: " + professional.provideCare());
        }

        System.out.println("\n=== PARTS 4-6: TODAY'S APPOINTMENTS ===");
        System.out.print("Available start times: ");
        for (String slot : DailySchedule.getTimeSlots()) {
            System.out.print(slot + " ");
        }
        System.out.println();
        AppointmentManager manager = new AppointmentManager();
        manager.displayAppointments(true);

        // Add deliberately out of chronological order, across all four staff types.
        Patient alice = Patient.create("Alice Green", "0400000001");
        Appointment first = Appointment.create(alice, "14:00", gpA);
        manager.addAppointment(first);
        manager.addAppointment("Ben Smith", "0400000002", "08:00", surgeon);
        manager.addAppointment("Cara Jones", "0400000003", "10:30", dietitian);
        manager.addAppointment("Alice Green", "0400000001", "09:00", nurse);
        manager.addAppointment("Daniel White", "0400000004", "14:00", gpB);
        manager.addAppointment("Eva King", "0400000005", "15:30", gpC);
        manager.addAppointment("Finn Scott", "0400000006", "08:30", gpA);

        manager.displayAppointments(false);
        manager.displayAppointments(true);
        manager.displayByProfessionalId(101, false);
        manager.displayByProfessionalId(101, true);
        manager.displayByPatientPhone("0400000001", false);
        manager.displayByPatientPhone("0400000001", true);

        System.out.println("\n=== EXPECTED WARNINGS: REJECTED OPERATIONS ===");
        manager.addAppointment("George Hill", "0400000007", "14:00", gpA);
        // Same ID, different Java object: identity-based conflict detection still works.
        GeneralPractitioner samePerson = GeneralPractitioner.create(101, "Dr Alex Chen", weekdays);
        System.out.println("Same professional by ID: " + gpA.equals(samePerson));
        System.out.println("Matching hash codes: " + (gpA.hashCode() == samePerson.hashCode()));
        manager.addAppointment("George Hill", "0400000007", "14:00", samePerson);
        manager.addAppointment(" ", "0400000008", "11:00", gpA);
        manager.addAppointment("Hannah Wood", "04-invalid", "11:00", gpA);
        manager.addAppointment("Hannah Wood", null, "11:00", gpA);
        manager.addAppointment("Hannah Wood", "0400000008", "08:15", gpA);
        manager.addAppointment("Hannah Wood", "0400000008", "16:00", gpA);
        manager.addAppointment("Hannah Wood", "0400000008", "8:00", gpA);
        manager.addAppointment("Hannah Wood", "0400000008", null, gpA);
        manager.addAppointment("Hannah Wood", "0400000008", "11:00", null);
        manager.addAppointment((Appointment) null);
        GeneralPractitioner.create(-1, "Invalid ID", weekdays);
        Nurse.create(402, "Invalid days", new String[] {"Funday"}, "Wound care");
        manager.displayByProfessionalId(999, true);
        manager.displayByPatientPhone("0499999999", true);

        System.out.println("\n=== CANCELLATIONS AND UPDATED COLLECTION ===");
        manager.cancelAppointment(201, "08:00");
        manager.cancelAppointment(201, "08:00");
        manager.cancelAppointment("0400000001");
        manager.cancelAppointment("0499999999");
        manager.displayAppointments(true);
        manager.displayByPatientPhone("0400000001", true);

        System.out.println("\n=== REBOOK A CANCELLED SLOT ===");
        manager.addAppointment("Ivy Adams", "0400000009", "14:00", gpA);
        manager.displayAppointments(true);
        System.out.println("\nFinal appointment count: " + manager.getAppointments().size());
    }
}
