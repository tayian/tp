package connectify.model.company;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CompanyIndustryTest {

    @Test
    public void equals() {
        CompanyIndustry companyIndustry = new CompanyIndustry("Client Services");

        // same values -> returns true
        assertTrue(companyIndustry.equals(new CompanyIndustry("Client Services")));

        // same object -> returns true
        assertTrue(companyIndustry.equals(companyIndustry));

        // null -> returns false
        assertFalse(companyIndustry.equals(null));

        // different types -> returns false
        assertFalse(companyIndustry.equals(5.0f));

        // different values -> returns false
        assertFalse(companyIndustry.equals(new CompanyIndustry("Consulting")));
    }
}
