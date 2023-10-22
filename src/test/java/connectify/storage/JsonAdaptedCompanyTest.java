package connectify.storage;

import static connectify.testutil.TypicalCompanies.COMPANY_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import connectify.commons.exceptions.IllegalValueException;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyPhone;
import connectify.testutil.Assert;

public class JsonAdaptedCompanyTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String VALID_NAME = COMPANY_1.getName().toString();
    private static final String VALID_INDUSTRY = COMPANY_1.getIndustry().toString();
    private static final String VALID_LOCATION = COMPANY_1.getLocation().toString();
    private static final String VALID_DESCRIPTION = COMPANY_1.getDescription().toString();
    private static final String VALID_WEBSITE = COMPANY_1.getWebsite().toString();
    private static final String VALID_EMAIL = COMPANY_1.getEmail().toString();
    private static final String VALID_PHONE = COMPANY_1.getPhone().toString();
    private static final String VALID_ADDRESS = COMPANY_1.getAddress().toString();

    private static final List<JsonAdaptedPerson> VALID_PEOPLE = COMPANY_1.getPersonList().asList()
            .stream()
            .map(JsonAdaptedPerson::new)
            .collect(Collectors.toList());

    /**
     * Tests if a valid company is successfully converted to its model type.
     */
    @Test
    public void toModelType_validCompanyDetails_returnsCompany() throws Exception {
        JsonAdaptedCompany company = new JsonAdaptedCompany(COMPANY_1);
        assertEquals(COMPANY_1, company.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(INVALID_NAME, VALID_INDUSTRY, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        VALID_EMAIL, VALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = CompanyName.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    /**
     * Tests if a null name in the company throws an {@code IllegalValueException}.
     */
    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(null, VALID_INDUSTRY, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        VALID_EMAIL, VALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = String.format(JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT, "CompanyName");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        VALID_EMAIL, INVALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = CompanyPhone.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    /**
     * Tests if a null phone number in the company throws an {@code IllegalValueException}.
     */
    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        VALID_EMAIL, null, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = String.format(JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT, "CompanyPhone");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    /**
     * Tests if a null industry in the company throws an {@code IllegalValueException}.
     */
    @Test
    public void toModelType_nullIndustry_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, null, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        VALID_EMAIL, VALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = String.format(JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT, "CompanyIndustry");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    /**
     * Tests if a null location in the company throws an {@code IllegalValueException}.
     */
    @Test
    public void toModelType_nullLocation_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, null,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        VALID_EMAIL, VALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = String.format(JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT, "CompanyLocation");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    /**
     * Tests if a null description in the company throws an {@code IllegalValueException}.
     */
    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, VALID_LOCATION, null, VALID_WEBSITE,
                        VALID_EMAIL, VALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = String.format(JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT, "Description");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    /**
     * Tests if a null website in the company throws an {@code IllegalValueException}.
     */
    @Test
    public void toModelType_nullWebsite_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, VALID_LOCATION, VALID_DESCRIPTION, null,
                        VALID_EMAIL, VALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = String.format(JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT, "CompanyWebsite");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        INVALID_EMAIL, VALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = CompanyEmail.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    /**
     * Tests if a null email in the company throws an {@code IllegalValueException}.
     */
    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        null, VALID_PHONE, VALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = String.format(JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT, "CompanyEmail");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        VALID_EMAIL, VALID_PHONE, INVALID_ADDRESS, VALID_PEOPLE);
        String expectedMessage = CompanyAddress.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }

    /**
     * Tests if a null address in the company throws an {@code IllegalValueException}.
     */
    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedCompany company =
                new JsonAdaptedCompany(VALID_NAME, VALID_INDUSTRY, VALID_LOCATION,
                        VALID_DESCRIPTION, VALID_WEBSITE,
                        VALID_EMAIL, VALID_PHONE, null, VALID_PEOPLE);
        String expectedMessage = String.format(JsonAdaptedCompany.MISSING_FIELD_MESSAGE_FORMAT, "CompanyAddress");
        Assert.assertThrows(IllegalValueException.class, expectedMessage, company::toModelType);
    }
}
