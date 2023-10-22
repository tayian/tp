package connectify.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompanyLocationTest {

    @Test
    public void equals() {
        CompanyLocation companyLocation = new CompanyLocation("Singapore");

        // same values -> returns true
        assertTrue(companyLocation.equals(new CompanyLocation("Singapore")));

        // same object -> returns true
        assertTrue(companyLocation.equals(companyLocation));

        // null -> returns false
        assertFalse(companyLocation.equals(null));

        // different types -> returns false
        assertFalse(companyLocation.equals(5.0f));

        // different values -> returns false
        assertFalse(companyLocation.equals(new CompanyLocation("Johor Bahru")));
    }
}
