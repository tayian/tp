package connectify.model.person;

import connectify.model.Email;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class PersonEmail extends Email {

    public PersonEmail(String email) {
        super(email);
    }

}
