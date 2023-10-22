package connectify.model.company;

import static connectify.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompanyAddressTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompanyAddress(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new CompanyAddress(invalidAddress));
    }

    @Test
    public void isValidAddress() {
        // null address
        assertThrows(NullPointerException.class, () -> CompanyAddress.isValidAddress(null));

        // invalid addresses
        assertFalse(CompanyAddress.isValidAddress("")); // empty string
        assertFalse(CompanyAddress.isValidAddress(" ")); // spaces only

        // valid addresses
        assertTrue(CompanyAddress.isValidAddress("Blk 456, Den Road, #01-355"));
        assertTrue(CompanyAddress.isValidAddress("-")); // one character
        assertTrue(CompanyAddress
            .isValidAddress("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }

    @Test
    public void equals() {
        CompanyAddress companyAddress = new CompanyAddress("Valid Address");

        // same values -> returns true
        assertTrue(companyAddress.equals(new CompanyAddress("Valid Address")));

        // same object -> returns true
        assertTrue(companyAddress.equals(companyAddress));

        // null -> returns false
        assertFalse(companyAddress.equals(null));

        // different types -> returns false
        assertFalse(companyAddress.equals(5.0f));

        // different values -> returns false
        assertFalse(companyAddress.equals(new CompanyAddress("Other Valid Address")));
    }
}
