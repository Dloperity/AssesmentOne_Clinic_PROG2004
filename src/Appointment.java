/** A complete appointment in today's in-memory collection. */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private String time;
    private HealthProfessional professional;

    private Appointment(Patient patient, String time, HealthProfessional professional) {
        this.patient = patient;
        this.time = time;
        this.professional = professional;
    }

    /** Creates a complete appointment, or warns and returns null. */
    public static Appointment create(Patient patient, String time, HealthProfessional professional) {
        if (patient == null || professional == null || !DailySchedule.isValidTime(time)) {
            System.out.println("WARNING: Appointment requires a patient, professional and valid clinic time.");
            return null;
        }
        return new Appointment(patient, time, professional);
    }

    /** Returns the patient. */
    public Patient getPatient() {
        return patient;
    }

    /** Returns the validated HH:mm start time. */
    public String getTime() {
        return time;
    }

    /** Returns the selected professional through the common parent type. */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /** Fixed-width 24-hour HH:mm strings sort in chronological order. */
    @Override
    public int compareTo(Appointment other) {
        return time.compareTo(other.time);
    }

    /** Composes the patient and professional's own descriptions. */
    @Override
    public String toString() {
        return "Today at " + time + " | " + patient + System.lineSeparator()
            + "  Professional: " + professional;
    }
}
