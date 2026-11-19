import java.util.ArrayList;
import java.util.Collections;

/** Manages today's appointments; no dates or persistent storage are accepted. */
public class AppointmentManager {
    private ArrayList<Appointment> appointments = new ArrayList<Appointment>();

    /** Adds a complete booking unless the professional already occupies that slot. */
    public boolean addAppointment(Appointment appointment) {
        if (appointment == null) {
            System.out.println("WARNING: Cannot add an incomplete appointment.");
            return false;
        }
        for (Appointment existing : appointments) {
            if (existing.getProfessional().equals(appointment.getProfessional())
                    && existing.getTime().equals(appointment.getTime())) {
                System.out.println("WARNING: Professional " + appointment.getProfessional().getId()
                    + " is already booked at " + appointment.getTime() + ".");
                return false;
            }
        }
        appointments.add(appointment);
        System.out.println("SUCCESS: Booked professional " + appointment.getProfessional().getId()
            + " at " + appointment.getTime() + ".");
        return true;
    }

    /** Convenience overload validates raw patient details before adding. */
    public boolean addAppointment(String name, String phone, String time,
            HealthProfessional professional) {
        Patient patient = Patient.create(name, phone);
        if (patient == null) {
            return false;
        }
        Appointment appointment = Appointment.create(patient, time, professional);
        if (appointment == null) {
            return false;
        }
        return addAppointment(appointment);
    }

    /** Returns a separate list so callers cannot alter the manager's collection. */
    public ArrayList<Appointment> getAppointments() {
        return new ArrayList<Appointment>(appointments);
    }

    /** Finds all bookings for a professional ID. */
    public ArrayList<Appointment> findByProfessionalId(int id) {
        ArrayList<Appointment> matches = new ArrayList<Appointment>();
        for (Appointment appointment : appointments) {
            if (appointment.getProfessional().getId() == id) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    /** Finds all bookings for an exact patient phone number. */
    public ArrayList<Appointment> findByPatientPhone(String phone) {
        ArrayList<Appointment> matches = new ArrayList<Appointment>();
        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getMobilePhone().equals(phone)) {
                matches.add(appointment);
            }
        }
        return matches;
    }

    /** Displays all bookings, optionally sorting a copy by start time. */
    public void displayAppointments(boolean sorted) {
        displayList("All appointments", getAppointments(), sorted);
    }

    /** Displays a professional's bookings, optionally in chronological order. */
    public void displayByProfessionalId(int id, boolean sorted) {
        displayList("Professional ID " + id, findByProfessionalId(id), sorted);
    }

    /** Displays a patient's bookings, optionally in chronological order. */
    public void displayByPatientPhone(String phone, boolean sorted) {
        displayList("Patient phone " + phone, findByPatientPhone(phone), sorted);
    }

    private void displayList(String title, ArrayList<Appointment> list, boolean sorted) {
        System.out.println("\n--- " + title + (sorted ? " (time order)" : " (insertion order)") + " ---");
        if (list.isEmpty()) {
            System.out.println("WARNING: No matching appointments.");
            return;
        }
        if (sorted) {
            Collections.sort(list);
        }
        for (Appointment appointment : list) {
            System.out.println(appointment);
        }
    }

    /** Cancels the unique booking identified by professional ID and start time. */
    public boolean cancelAppointment(int id, String time) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            if (appointment.getProfessional().getId() == id && appointment.getTime().equals(time)) {
                appointments.remove(i);
                System.out.println("SUCCESS: Cancelled professional " + id + " at " + time + ".");
                return true;
            }
        }
        System.out.println("WARNING: No appointment to cancel for professional " + id + " at " + time + ".");
        return false;
    }

    /** Cancels every booking for the phone number, returning the number removed. */
    public int cancelAppointment(String phone) {
        int removed = 0;
        // Work backwards so removing an element cannot skip the next matching entry.
        for (int i = appointments.size() - 1; i >= 0; i--) {
            if (appointments.get(i).getPatient().getMobilePhone().equals(phone)) {
                appointments.remove(i);
                removed++;
            }
        }
        if (removed == 0) {
            System.out.println("WARNING: No appointments to cancel for phone " + phone + ".");
        } else {
            System.out.println("SUCCESS: Cancelled " + removed + " appointment(s) for phone " + phone + ".");
        }
        return removed;
    }
}
