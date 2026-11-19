/** Common identity, weekly working days and behaviour for clinical staff. */
public abstract class HealthProfessional {
    private int id;
    private String name;
    private String[] workingDays;
    private static final String[] WEEK_DAYS = {
        "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
    };

    /** Initialises shared details after a concrete factory has validated them. */
    protected HealthProfessional(int id, String name, String[] workingDays) {
        this.id = id;
        this.name = name.trim();
        this.workingDays = new String[workingDays.length];
        for (int i = 0; i < workingDays.length; i++) {
            this.workingDays[i] = workingDays[i];
        }
    }

    /** Checks the common values without creating an invalid object. */
    protected static boolean validDetails(int id, String name, String[] days) {
        if (id < 0 || !Validation.hasText(name) || days == null || days.length == 0) {
            return false;
        }
        for (int i = 0; i < days.length; i++) {
            boolean recognised = false;
            for (String day : WEEK_DAYS) {
                if (day.equals(days[i])) {
                    recognised = true;
                }
            }
            if (!recognised) {
                return false;
            }
            for (int j = 0; j < i; j++) {
                if (days[i].equals(days[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the numeric identity shared by all representations of this person. */
    public int getId() {
        return id;
    }

    /** Returns the professional's name. */
    public String getName() {
        return name;
    }

    /** Returns a defensive copy of weekly working days. */
    public String[] getWorkingDays() {
        String[] copy = new String[workingDays.length];
        for (int i = 0; i < workingDays.length; i++) {
            copy[i] = workingDays[i];
        }
        return copy;
    }

    /** Each concrete professional supplies their own type label. */
    public abstract String getType();

    /** Each concrete professional describes their own clinical service. */
    public abstract String provideCare();

    /** Professionals with the same ID represent the same person. */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HealthProfessional)) {
            return false;
        }
        HealthProfessional professional = (HealthProfessional) other;
        return id == professional.id;
    }

    /** Uses the same identity as equals. */
    @Override
    public int hashCode() {
        return id;
    }

    /** Returns common details; concrete classes append their extra information. */
    @Override
    public String toString() {
        String days = "";
        for (int i = 0; i < workingDays.length; i++) {
            if (i > 0) {
                days += ", ";
            }
            days += workingDays[i];
        }
        return getType() + " | ID: " + id + " | Name: " + name + " | Working days: " + days;
    }
}
