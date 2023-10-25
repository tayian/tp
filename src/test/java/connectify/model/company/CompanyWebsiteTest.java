package connectify.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompanyWebsiteTest {

    @Test
    public void equals() {
        CompanyWebsite companyWebsite = new CompanyWebsite("facebook.google.com");

        // same values -> returns true
        assertTrue(companyWebsite.equals(new CompanyWebsite("facebook.google.com")));

        // same object -> returns true
        assertTrue(companyWebsite.equals(companyWebsite));

        // null -> returns false
        assertFalse(companyWebsite.equals(null));

        // different types -> returns false
        assertFalse(companyWebsite.equals(5.0f));

        // different values -> returns false
        assertFalse(companyWebsite.equals(new CompanyWebsite("facebook.com")));
    }
}
