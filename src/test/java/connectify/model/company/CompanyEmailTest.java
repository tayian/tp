package connectify.model.company;

import static connectify.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompanyEmailTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyEmail(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyEmail(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null email
        assertThrows(NullPointerException.class, () -> CompanyEmail.isValidEmail(null));

        // blank email
        assertFalse(CompanyEmail.isValidEmail("")); // empty string
        assertFalse(CompanyEmail.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(CompanyEmail.isValidEmail("@example.com")); // missing local part
        assertFalse(CompanyEmail.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(CompanyEmail.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(CompanyEmail.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(CompanyEmail.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(CompanyEmail.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(CompanyEmail.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(CompanyEmail.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(CompanyEmail.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(CompanyEmail.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(CompanyEmail.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(CompanyEmail.isValidEmail("-peterjack@example.com")); // local part starts with a hyphen
        assertFalse(CompanyEmail.isValidEmail("peterjack-@example.com")); // local part ends with a hyphen
        assertFalse(CompanyEmail.isValidEmail("peter..jack@example.com")); // local part has two consecutive periods
        assertFalse(CompanyEmail.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(CompanyEmail.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(CompanyEmail.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(CompanyEmail.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(CompanyEmail.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen
        assertFalse(CompanyEmail.isValidEmail("peterjack@example.c")); // top level domain has less than two chars

        // valid email
        assertTrue(CompanyEmail.isValidEmail("PeterJack_1190@example.com")); // underscore in local part
        assertTrue(CompanyEmail.isValidEmail("PeterJack.1190@example.com")); // period in local part
        assertTrue(CompanyEmail.isValidEmail("PeterJack+1190@example.com")); // '+' symbol in local part
        assertTrue(CompanyEmail.isValidEmail("PeterJack-1190@example.com")); // hyphen in local part
        assertTrue(CompanyEmail.isValidEmail("a@bc")); // minimal
        assertTrue(CompanyEmail.isValidEmail("test@localhost")); // alphabets only
        assertTrue(CompanyEmail.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(CompanyEmail.isValidEmail("a1+be.d@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(CompanyEmail.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(CompanyEmail.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
        assertTrue(CompanyEmail.isValidEmail("e1234567@u.nus.edu")); // more than one period in domain
    }

    @Test
    public void equals() {
        CompanyEmail companyEmail = new CompanyEmail("valid@email");

        // same values -> returns true
        assertTrue(companyEmail.equals(new CompanyEmail("valid@email")));

        // same object -> returns true
        assertTrue(companyEmail.equals(companyEmail));

        // null -> returns false
        assertFalse(companyEmail.equals(null));

        // different types -> returns false
        assertFalse(companyEmail.equals(5.0f));

        // different values -> returns false
        assertFalse(companyEmail.equals(new CompanyEmail("other.valid@email")));
    }
}
