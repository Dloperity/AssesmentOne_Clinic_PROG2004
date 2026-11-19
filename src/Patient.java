/** Basic patient details held by an appointment. */
public class Patient {
    private String name;
    private String mobilePhone;

    private Patient(String name, String mobilePhone) {
        this.name = name.trim();
        this.mobilePhone = mobilePhone;
    }

    /** Validates before construction; phone numbers remain strings to retain zeroes. */
    public static Patient create(String name, String mobilePhone) {
        if (!Validation.hasText(name) || !Validation.digitsOnly(mobilePhone)) {
            System.out.println("WARNING: A patient needs a name and a digits-only mobile number.");
            return null;
        }
        return new Patient(name, mobilePhone);
    }

    /** Returns the patient's name. */
    public String getName() {
        return name;
    }

    /** Returns the patient's mobile number. */
    public String getMobilePhone() {
        return mobilePhone;
    }

    @Override
    public String toString() {
        return "Patient: " + name + " | Mobile: " + mobilePhone;
    }
}
