package connectify.model.company;

import connectify.model.Address;

/**
 * Represents a Company's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class CompanyAddress extends Address {

    /**
     * Constructs an {@code Address}.
     *
     * @param address A valid address.
     */
    public CompanyAddress(String address) {
        super(address);
    }

}
