/** A dietitian with a particular nutrition focus. */
public class Dietitian extends HealthProfessional {
    private String nutritionFocus;

    private Dietitian(int id, String name, String[] days, String focus) {
        super(id, name, days);
        nutritionFocus = focus.trim();
    }

    /** Creates a dietitian, or warns and returns null for invalid details. */
    public static Dietitian create(int id, String name, String[] days, String focus) {
        if (!validDetails(id, name, days) || !Validation.hasText(focus)) {
            System.out.println("WARNING: Invalid dietitian details.");
            return null;
        }
        return new Dietitian(id, name, days, focus);
    }

    /** Returns the nutrition focus. */
    public String getNutritionFocus() {
        return nutritionFocus;
    }

    @Override
    public String getType() {
        return "Dietitian";
    }

    @Override
    public String provideCare() {
        return "Develops nutrition plans for " + nutritionFocus + ".";
    }

    @Override
    public String toString() {
        return super.toString() + " | Nutrition focus: " + nutritionFocus;
    }
}
