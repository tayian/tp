package connectify.testutil;

import connectify.model.company.Company;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyIndustry;
import connectify.model.company.CompanyLocation;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyPhone;
import connectify.model.company.CompanyWebsite;
import connectify.model.person.PersonList;

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
    public static final String DEFAULT_EMAIL = "google@gmail.com";

    public static final String DEFAULT_PHONE = "12345678";
    public static final String DEFAULT_ADDRESS = "1600 Amphitheatre Parkway, Mountain View, CA 94043, USA";
    private CompanyName name;
    private CompanyIndustry industry;
    private CompanyLocation location;
    private String description;
    private CompanyWebsite website;
    private CompanyEmail email;
    private CompanyPhone phone;
    private CompanyAddress address;

    private PersonList personList;

    /**
     * Creates a {@code CompanyBuilder} with the default details.
     */
    public CompanyBuilder() {
        this.name = new CompanyName(DEFAULT_NAME);
        this.industry = new CompanyIndustry(DEFAULT_INDUSTRY);
        this.location = new CompanyLocation(DEFAULT_LOCATION);
        this.description = DEFAULT_DESCRIPTION;
        this.website = new CompanyWebsite(DEFAULT_WEBSITE);
        this.email = new CompanyEmail(DEFAULT_EMAIL);
        this.phone = new CompanyPhone(DEFAULT_PHONE);
        this.address = new CompanyAddress(DEFAULT_ADDRESS);
        this.personList = new PersonList();
    }

    /**
     * Initializes the CompanyBuilder with the data of {@code companyToCopy}.
     * @param companyToCopy
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
        this.personList = companyToCopy.getPersonList();
    }

    /**
     * Sets the {@code Name} of the {@code Company} that we are building.
     */
    public CompanyBuilder withName(String name) {
        this.name = new CompanyName(name);
        return this;
    }

    /**
     * Sets the {@code Industry} of the {@code Company} that we are building.
     */
    public CompanyBuilder withIndustry(String industry) {
        this.industry = new CompanyIndustry(industry);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Company} that we are building.
     */
    public CompanyBuilder withLocation(String location) {
        this.location = new CompanyLocation(location);
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
        this.website = new CompanyWebsite(website);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Company} that we are building.
     */
    public CompanyBuilder withEmail(String email) {
        this.email = new CompanyEmail(email);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Company} that we are building.
     */
    public CompanyBuilder withPhone(String phone) {
        this.phone = new CompanyPhone(phone);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Company} that we are building.
     */
    public CompanyBuilder withAddress(String address) {
        this.address = new CompanyAddress(address);
        return this;
    }

    /**
     * Sets the {@code PersonList} of the {@code Company} that we are building.
     */
    public CompanyBuilder withPersonList(PersonList personList) {
        this.personList = personList;
        return this;
    }

    /**
     * Builds a company.
     * @return Company
     */
    public Company build() {
        return new Company(name, industry, location, description, website, email, phone, address, personList);
    }
}
