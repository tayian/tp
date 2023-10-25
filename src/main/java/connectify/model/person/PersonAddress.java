package connectify.model.person;

import connectify.model.Address;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class PersonAddress extends Address {

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public PersonAddress(String address) {
        super(address);
    }

}
