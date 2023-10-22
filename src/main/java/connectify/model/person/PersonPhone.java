package connectify.model.person;

import connectify.model.Phone;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class PersonPhone extends Phone {

    /**
     * Constructs a {@code Phone}.
     *
     * @param phone A valid phone number.
     */
    public PersonPhone(String phone) {
        super(phone);
    }

}
