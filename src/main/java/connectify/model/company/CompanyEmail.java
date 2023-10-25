package connectify.model.company;

import connectify.model.Email;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class CompanyEmail extends Email {

    public CompanyEmail(String email) {
        super(email);
    }

}
