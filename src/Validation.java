/** Shared checks used before constructing domain objects. */
public class Validation {
    private Validation() {
    }

    /** Returns whether text contains a non-whitespace character. */
    public static boolean hasText(String text) {
        return text != null && !text.trim().isEmpty();
    }

    /** Checks ASCII digits while preserving leading zeroes in phone numbers. */
    public static boolean digitsOnly(String text) {
        if (!hasText(text)) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) < '0' || text.charAt(i) > '9') {
                return false;
            }
        }
        return true;
    }
}
