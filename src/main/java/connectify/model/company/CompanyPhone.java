package connectify.model.company;

import connectify.model.Phone;

/**
 * Represents a Company's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class CompanyPhone extends Phone {

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public CompanyPhone(String phone) {
        super(phone);
    }

}
