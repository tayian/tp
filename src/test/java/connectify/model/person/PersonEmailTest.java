package connectify.model.person;

import static connectify.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PersonEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonEmail(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new PersonEmail(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> PersonEmail.isValidEmail(null));

        // blank email
        assertFalse(PersonEmail.isValidEmail("")); // empty string
        assertFalse(PersonEmail.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(PersonEmail.isValidEmail("@example.com")); // missing local part
        assertFalse(PersonEmail.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(PersonEmail.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(PersonEmail.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(PersonEmail.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(PersonEmail.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(PersonEmail.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(PersonEmail.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(PersonEmail.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(PersonEmail.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(PersonEmail.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(PersonEmail.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(PersonEmail.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(PersonEmail.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(PersonEmail.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(PersonEmail.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(PersonEmail.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(PersonEmail.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(PersonEmail.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(PersonEmail.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(PersonEmail.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(PersonEmail.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(PersonEmail.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(PersonEmail.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(PersonEmail.isValidEmail("a@bc")); // minimal
        assertTrue(PersonEmail.isValidEmail("test@localhost")); // alphabets only
        assertTrue(PersonEmail.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(PersonEmail.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(PersonEmail.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(PersonEmail.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(PersonEmail.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void equals() {
        PersonEmail personEmail = new PersonEmail("valid@email");

        // same values -> returns true
        assertTrue(personEmail.equals(new PersonEmail("valid@email")));

        // same object -> returns true
        assertTrue(personEmail.equals(personEmail));

        // null -> returns false
        assertFalse(personEmail.equals(null));

        // different types -> returns false
        assertFalse(personEmail.equals(5.0f));

        // different values -> returns false
        assertFalse(personEmail.equals(new PersonEmail("other.valid@email")));
    }
}
