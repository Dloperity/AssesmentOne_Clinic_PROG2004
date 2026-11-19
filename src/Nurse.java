/** A nurse with a recorded clinical skill. */
public class Nurse extends HealthProfessional {
    private String clinicalSkill;

    private Nurse(int id, String name, String[] days, String skill) {
        super(id, name, days);
        clinicalSkill = skill.trim();
    }

    /** Creates a nurse, or warns and returns null for invalid details. */
    public static Nurse create(int id, String name, String[] days, String skill) {
        if (!validDetails(id, name, days) || !Validation.hasText(skill)) {
            System.out.println("WARNING: Invalid nurse details.");
            return null;
        }
        return new Nurse(id, name, days, skill);
    }

    /** Returns the clinical skill. */
    public String getClinicalSkill() {
        return clinicalSkill;
    }

    @Override
    public String getType() {
        return "Nurse";
    }

    @Override
    public String provideCare() {
        return "Provides nursing care, including " + clinicalSkill + ".";
    }

    @Override
    public String toString() {
        return super.toString() + " | Clinical skill: " + clinicalSkill;
    }
}
