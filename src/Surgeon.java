/** A surgeon whose additional information is their surgical specialty. */
public class Surgeon extends HealthProfessional {
    private String surgicalSpecialty;

    private Surgeon(int id, String name, String[] days, String specialty) {
        super(id, name, days);
        surgicalSpecialty = specialty.trim();
    }

    /** Creates a surgeon, or warns and returns null for invalid details. */
    public static Surgeon create(int id, String name, String[] days, String specialty) {
        if (!validDetails(id, name, days) || !Validation.hasText(specialty)) {
            System.out.println("WARNING: Invalid surgeon details.");
            return null;
        }
        return new Surgeon(id, name, days, specialty);
    }

    /** Returns the surgical specialty. */
    public String getSurgicalSpecialty() {
        return surgicalSpecialty;
    }

    @Override
    public String getType() {
        return "Surgeon";
    }

    @Override
    public String provideCare() {
        return "Provides surgical consultations in " + surgicalSpecialty + ".";
    }

    @Override
    public String toString() {
        return super.toString() + " | Surgical specialty: " + surgicalSpecialty;
    }
}
