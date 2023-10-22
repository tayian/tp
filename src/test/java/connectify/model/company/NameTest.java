package connectify.model.company;

import static connectify.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> CompanyName.isValidName(null));

        // invalid name
        assertFalse(CompanyName.isValidName("")); // empty string
        assertFalse(CompanyName.isValidName(" ")); // spaces only
        assertFalse(CompanyName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(CompanyName.isValidName("pteltd*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(CompanyName.isValidName("alphabet netflix")); // alphabets only
        assertTrue(CompanyName.isValidName("12345")); // numbers only
        assertTrue(CompanyName.isValidName("peter industries")); // alphanumeric characters
        assertTrue(CompanyName.isValidName("Capital Tan Pte Ltd")); // with capital letters
        assertTrue(CompanyName.isValidName("David Roger Jackson Ray Jr 2nd LLC")); // long names
    }

    @Test
    public void equals() {
        CompanyName name = new CompanyName("Valid Name");

        // same values -> returns true
        assertTrue(name.equals(new CompanyName("Valid Name")));

        // same object -> returns true
        assertTrue(name.equals(name));

        // null -> returns false
        assertFalse(name.equals(null));

        // different types -> returns false
        assertFalse(name.equals(5.0f));

        // different values -> returns false
        assertFalse(name.equals(new CompanyName("Other Valid Name")));
    }
}
