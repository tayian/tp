package connectify.model.company;

import static connectify.commons.util.CollectionUtil.requireAllNonNull;

import connectify.commons.util.ToStringBuilder;
import connectify.model.Entity;
import connectify.model.person.Person;
import connectify.model.person.PersonList;

/**
 * Represents a Company in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Company extends Entity {
    private final CompanyName name;
    private final CompanyIndustry industry;
    private final CompanyLocation location;
    private final String description;
    private final CompanyWebsite website;
    private final CompanyEmail email;
    private final CompanyPhone phone;
    private final CompanyAddress address;

    private final PersonList personList;

    /**
     * Constructor for Company, with PersonList declared.
     * @param name Name of company
     * @param industry Industry of company
     * @param location Location of company
     * @param description Description of company
     * @param website Website of company
     * @param email Email of company
     * @param phone Phone of company
     * @param address Address of company
     */
    public Company(CompanyName name, CompanyIndustry industry, CompanyLocation location,
                   String description, CompanyWebsite website, CompanyEmail email,
                   CompanyPhone phone, CompanyAddress address, PersonList personList) {
        requireAllNonNull(name, industry, location, description, website, email, phone, address);
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.personList = personList;
    }

    /**
     * Alternative Constructor for Company, with empty PersonList.
     * @param name Name of company
     * @param industry Industry of company
     * @param location Location of company
     * @param description Description of company
     * @param website Website of company
     * @param email Email of company
     * @param phone Phone of company
     * @param address Address of company
     */
    public Company(CompanyName name, CompanyIndustry industry, CompanyLocation location,
                   String description, CompanyWebsite website, CompanyEmail email,
                   CompanyPhone phone, CompanyAddress address) {
        requireAllNonNull(name, industry, location, description, website, email, phone, address);
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.personList = new PersonList();
    }

    /**
     * Returns the person list of the company.
     * @return Person list of company
     */
    public PersonList getPersonList() {
        return personList;
    }

    /**
     * Adds a person to the company.
     * @param person Person to be added
     */
    public Company addPersonToCompany(Person person) {
        requireAllNonNull(person);
        PersonList edited = new PersonList(personList).addPerson(person);
        return new Company(name, industry, location, description, website, email, phone, address, edited);
    }

    /**
     * Deletes a person from the company.
     * @param person Person to be removed
     * @return New Company object with the person removed
     */
    public Company deletePersonFromCompany(Person person) {
        requireAllNonNull(person);
        PersonList edited = new PersonList(personList).removePerson(person);
        return new Company(name, industry, location, description, website, email, phone, address, edited);
    }

    /**
     * Returns the name of the company.
     * @return Name of company
     */
    public CompanyName getName() {
        return name;
    }

    /**
     * Returns the industry of the company.
     * @return Industry of company
     */
    public CompanyIndustry getIndustry() {
        return industry;
    }

    /**
     * Returns the location of the company.
     * @return Location of company
     */
    public CompanyLocation getLocation() {
        return location;
    }

    /**
     * Returns the description of the company.
     * @return Description of company
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the website of the company.
     * @return Website of company
     */
    public CompanyWebsite getWebsite() {
        return website;
    }

    /**
     * Returns the email of the company.
     * @return Email of company
     */
    public CompanyEmail getEmail() {
        return email;
    }

    /**
     * Returns the phone number of the company.
     * @return Phone number of company
     */
    public CompanyPhone getPhone() {
        return phone;
    }

    /**
     * Returns the address of the company.
     * @return Address of company
     */
    public CompanyAddress getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Company)) {
            return false;
        }

        Company otherCompany = (Company) other;
        return otherCompany.getName().equals(getName())
                && otherCompany.getIndustry().equals(getIndustry())
                && otherCompany.getLocation().equals(getLocation())
                && otherCompany.getDescription().equals(getDescription())
                && otherCompany.getWebsite().equals(getWebsite())
                && otherCompany.getEmail().equals(getEmail())
                && otherCompany.getPhone().equals(getPhone())
                && otherCompany.getAddress().equals(getAddress())
                && otherCompany.getPersonList().equals(getPersonList());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Returns true if both companies have the same name.
     * This defines a weaker notion of equality between two companies.
     */
    public boolean isSameCompany(Company otherCompany) {
        if (otherCompany == this) {
            return true;
        }

        if (otherCompany == null) {
            return false;
        }

        return otherCompany.getName().equals(getName());
    }

    @Override
    public String toString() {
        ToStringBuilder string = new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("industry", industry)
                .add("location", location)
                .add("description", description)
                .add("website", website)
                .add("people", personList);
        return string.toString();
    }

}
