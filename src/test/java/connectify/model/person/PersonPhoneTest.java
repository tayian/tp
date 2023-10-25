package connectify.model.person;

import static connectify.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PersonPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> PersonPhone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(PersonPhone.isValidPhone("")); // empty string
        assertFalse(PersonPhone.isValidPhone(" ")); // spaces only
        assertFalse(PersonPhone.isValidPhone("91")); // less than 3 numbers
        assertFalse(PersonPhone.isValidPhone("phone")); // non-numeric
        assertFalse(PersonPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(PersonPhone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(PersonPhone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(PersonPhone.isValidPhone("93121534"));
        assertTrue(PersonPhone.isValidPhone("124293842033123")); // long phone numbers
    }

    @Test
    public void equals() {
        PersonPhone personPhone = new PersonPhone("999");

        // same values -> returns true
        assertTrue(personPhone.equals(new PersonPhone("999")));

        // same object -> returns true
        assertTrue(personPhone.equals(personPhone));

        // null -> returns false
        assertFalse(personPhone.equals(null));

        // different types -> returns false
        assertFalse(personPhone.equals(5.0f));

        // different values -> returns false
        assertFalse(personPhone.equals(new PersonPhone("995")));
    }
}
