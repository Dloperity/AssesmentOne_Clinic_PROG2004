/** A general practitioner with a consultation focus. */
public class GeneralPractitioner extends HealthProfessional {
    private String consultationFocus;
    private static final String DEFAULT_FOCUS = "General family health";

    private GeneralPractitioner(int id, String name, String[] days, String focus) {
        super(id, name, days);
        consultationFocus = focus.trim();
    }

    /** Constructor chaining supplies the default consultation focus. */
    private GeneralPractitioner(int id, String name, String[] days) {
        this(id, name, days, DEFAULT_FOCUS);
    }

    /** Creates a GP, or warns and returns null if details are invalid. */
    public static GeneralPractitioner create(int id, String name, String[] days, String focus) {
        if (!validDetails(id, name, days) || !Validation.hasText(focus)) {
            System.out.println("WARNING: Invalid general practitioner details.");
            return null;
        }
        return new GeneralPractitioner(id, name, days, focus);
    }

    /** Creates a GP with the default consultation focus. */
    public static GeneralPractitioner create(int id, String name, String[] days) {
        if (!validDetails(id, name, days)) {
            System.out.println("WARNING: Invalid general practitioner details.");
            return null;
        }
        return new GeneralPractitioner(id, name, days);
    }

    /** Returns the consultation focus. */
    public String getConsultationFocus() {
        return consultationFocus;
    }

    @Override
    public String getType() {
        return "General practitioner";
    }

    @Override
    public String provideCare() {
        return "Provides general consultations and prescribes medication when appropriate.";
    }

    @Override
    public String toString() {
        return super.toString() + " | Consultation focus: " + consultationFocus;
    }
}
