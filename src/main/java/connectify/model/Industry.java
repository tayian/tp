package connectify.model;

/**
 * Represents an industry in the address book.
 */
public abstract class Industry {

    public final String value;

    /**
     * Constructs an {@code Industry}.
     *
     * @param industry A valid industry.
     */
    public Industry(String industry) {
        value = industry;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Industry)) {
            return false;
        }

        Industry otherIndustry = (Industry) other;
        return value.equals(otherIndustry.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
