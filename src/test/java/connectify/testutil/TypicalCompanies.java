package connectify.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import connectify.model.AddressBook;
import connectify.model.company.Company;
import connectify.model.person.Person;

/**
 * A utility class to help with building Company objects.
 */
public class TypicalCompanies {
    public static final Company COMPANY_1 = new CompanyBuilder().withName("Company1").withPhone("64232346")
            .withEmail("company1@gmail.com").withAddress("Blk 456, Ang Mo Kio Ave 10, #-09-123")
            .withIndustry("Customer Service").withLocation("Blk 345, Yio Chu Kang Ave")
            .withAffiliatedPersons(createTypicalAffiliatedPersons()).build();
    public static final Company COMPANY_2 = new CompanyBuilder().withName("Banana").withPhone("83464463")
            .withEmail("company2@gmail.com").withAddress("Blk 456, Ang Mo Kio Ave 10, #-09-123")
            .withIndustry("Retail").withLocation("Blk 234, Khatib Drive")
            .withAffiliatedPersons(createTypicalAffiliatedPersons())
            .build();

    private TypicalCompanies() {} // prevents instantiation

    private static List<Person> createTypicalAffiliatedPersons() {
        List<Person> typicalAffiliatedPersons = new ArrayList<>();

        PersonBuilder personBuilder = new PersonBuilder();

        // Create the first person with custom details
        Person person1 = personBuilder
                .withName("Tan Ah Kow")
                .withPhone("876543210")
                .withEmail("ahkow.tan@example.com")
                .withAddress("123 Bukit Timah Road, #08-15")
                .build();

        // Create the second person with custom details
        Person person2 = personBuilder
                .withName("Lily Ng")
                .withPhone("234567890")
                .withEmail("lily.ng@hotmail.com")
                .withAddress("456 Orchard Road, #21-33")
                .build();

        // Add persons to the list
        typicalAffiliatedPersons.add(person1);
        typicalAffiliatedPersons.add(person2);

        return typicalAffiliatedPersons;
    }

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Company company : getTypicalCompanies()) {
            ab.addCompany(company);
        }
        return ab;
    }

    public static List<Company> getTypicalCompanies() {
        return new ArrayList<>(Arrays.asList(COMPANY_1, COMPANY_2));
    }
}
