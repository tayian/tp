package connectify.model;

/**
 * Represents a location in the address book.
 */
public abstract class Location {

    public final String value;

    /**
     * Constructs a {@code Location}.
     *
     * @param location A valid location.
     */
    public Location(String location) {
        value = location;
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
        if (!(other instanceof Location)) {
            return false;
        }

        Location otherLocation = (Location) other;
        return value.equals(otherLocation.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
