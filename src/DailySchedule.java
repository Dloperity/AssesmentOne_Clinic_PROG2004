/** Owns the clinic's fixed, chronological list of appointment start times. */
public class DailySchedule {
    private static final String[] TIME_SLOTS = {
        "08:00", "08:30", "09:00", "09:30", "10:00", "10:30",
        "11:00", "11:30", "12:00", "12:30", "13:00", "13:30",
        "14:00", "14:30", "15:00", "15:30"
    };

    private DailySchedule() {
    }

    /** Returns whether a time exactly matches one of the clinic's slots. */
    public static boolean isValidTime(String time) {
        for (String slot : TIME_SLOTS) {
            if (slot.equals(time)) {
                return true;
            }
        }
        return false;
    }

    /** Returns a copy so callers cannot change the shared schedule. */
    public static String[] getTimeSlots() {
        String[] copy = new String[TIME_SLOTS.length];
        for (int i = 0; i < TIME_SLOTS.length; i++) {
            copy[i] = TIME_SLOTS[i];
        }
        return copy;
    }
}
