package connectify.model.company;

import connectify.model.Name;

/**
 * Represents a Company's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class CompanyName extends Name {

    /**
     * Constructs a {@code Name}.
     *
     * @param name A valid name.
     */
    public CompanyName(String name) {
        super(name);
    }

}
