package connectify.model;

/**
 * Represents a website in the address book.
 */
public abstract class Website {

    public final String value;

    /**
     * Constructs a {@code Website}.
     *
     * @param website A valid website.
     */
    public Website(String website) {
        value = website;
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
        if (!(other instanceof Website)) {
            return false;
        }

        Website otherWebsite = (Website) other;
        return value.equals(otherWebsite.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
