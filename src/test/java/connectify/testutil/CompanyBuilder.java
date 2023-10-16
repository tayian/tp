package connectify.testutil;

import connectify.model.company.Company;
import connectify.model.person.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * A utility class to help with building Company objects.
 */
public class CompanyBuilder {
    public static final String DEFAULT_NAME = "Google";
    public static final String DEFAULT_INDUSTRY = "Technology";
    public static final String DEFAULT_LOCATION = "Singapore";
    public static final String DEFAULT_DESCRIPTION = "Google is an American multinational technology company "
            + "specializing in Internet-related services and products.";
    public static final String DEFAULT_WEBSITE = "https://www.google.com/";
    public static final String DEFAULT_EMAIL = "";

    public static final String DEFAULT_PHONE = "12345678";
    public static final String DEFAULT_ADDRESS = "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA";
    public static final List<Person> DEFAULT_AFFILIATEDPERSONS = createDefaultAffiliatedPersons();

    private String name;
    private String industry;
    private String location;
    private String description;
    private String website;
    private String email;
    private String phone;
    private String address;
    private List<Person> affiliatedPersons;


    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        this.name = DEFAULT_NAME;
        this.industry = DEFAULT_INDUSTRY;
        this.location = DEFAULT_LOCATION;
        this.description = DEFAULT_DESCRIPTION;
        this.website = DEFAULT_WEBSITE;
        this.email = DEFAULT_EMAIL;
        this.phone = DEFAULT_PHONE;
        this.address = DEFAULT_ADDRESS;
        this.affiliatedPersons = DEFAULT_AFFILIATEDPERSONS;
    }

    private static List<Person> createDefaultAffiliatedPersons() {
        List<Person> defaultAffiliatedPersons = new ArrayList<>();

        PersonBuilder personBuilder = new PersonBuilder();

        // Create the first person with custom details
        Person person1 = personBuilder
                .withName("John Doe")
                .withPhone("4567891011")
                .withEmail("john.doe@example.com")
                .withAddress("789 Elm St")
                .build();

        // Create the second person with custom details
        Person person2 = personBuilder
                .withName("Jane Lim")
                .withPhone("987654321")
                .withEmail("jane.limi@gmail.com")
                .withAddress("342 Tampines Central 2, #15-42")
                .build();

        // Add persons to the list
        defaultAffiliatedPersons.add(person1);
        defaultAffiliatedPersons.add(person2);

        return defaultAffiliatedPersons;
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code companyToCopy}.
     * @param companyToCopy Company to copy
     */
    public CompanyBuilder(Company companyToCopy) {
        this.name = companyToCopy.getName();
        this.industry = companyToCopy.getIndustry();
        this.location = companyToCopy.getLocation();
        this.description = companyToCopy.getDescription();
        this.website = companyToCopy.getWebsite();
        this.email = companyToCopy.getEmail();
        this.phone = companyToCopy.getPhone();
        this.address = companyToCopy.getAddress();
        this.affiliatedPersons = companyToCopy.getAffiliatedPersons();
    }

    /**
     * Sets the {@code Name} of the {@code Company} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code Company} that we are building.
     */
    public CompanyBuilder withIndustry(String industry) {
        this.industry = industry;
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Company} that we are building.
     */
    public CompanyBuilder withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Company} that we are building.
     */
    public CompanyBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the {@code Website} of the {@code Company} that we are building.
     */
    public CompanyBuilder withWebsite(String website) {
        this.website = website;
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Company} that we are building.
     */
    public CompanyBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Company} that we are building.
     */
    public CompanyBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Company} that we are building.
     */
    public CompanyBuilder withAddress(String address) {
        this.address = address;
        return this;
    }

    /**
     * Sets the {@code AffiliatedPersons} of the {@code Company} that we are building.
     */
    public CompanyBuilder withAffiliatedPersons(List<Person> affiliatedPersons) {
        this.affiliatedPersons = affiliatedPersons;
        return this;
    }

    /**
     * Builds a company.
     * @return Company
     */
    public Company build() {
        return new Company(name, industry, location, description, website, email, phone, address, affiliatedPersons);
    }
}
