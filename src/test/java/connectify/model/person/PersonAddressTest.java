package connectify.model.person;

import static connectify.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PersonAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonAddress(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonAddress(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> PersonAddress.isValidAddress(null));

        // invalid addresses
        assertFalse(PersonAddress.isValidAddress("")); // empty string
        assertFalse(PersonAddress.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(PersonAddress.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(PersonAddress.isValidAddress("-")); // one character
        assertTrue(PersonAddress
            .isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        PersonAddress personAddress = new PersonAddress("Valid Address");

        // same values -> returns true
        assertTrue(personAddress.equals(new PersonAddress("Valid Address")));

        // same object -> returns true
        assertTrue(personAddress.equals(personAddress));

        // null -> returns false
        assertFalse(personAddress.equals(null));

        // different types -> returns false
        assertFalse(personAddress.equals(5.0f));

        // different values -> returns false
        assertFalse(personAddress.equals(new PersonAddress("Other Valid Address")));
    }
}
