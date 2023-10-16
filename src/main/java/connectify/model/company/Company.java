package connectify.model.company;

import static connectify.commons.util.CollectionUtil.requireAllNonNull;

import connectify.commons.util.ToStringBuilder;
import connectify.model.person.Person;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Company in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Company {
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
     * Constructor for Company.
     * @param name Name of company
     * @param industry Industry of company
     * @param location Location of company
     * @param description Description of company
     * @param website Website of company
     * @param email Email of company
     * @param phone Phone of company
     * @param address Address of company
     */
    public Company(String name, String industry, String location, String description, String website, String email,
                   String phone, String address) {
        requireAllNonNull(name, industry, location, description, website, email, phone, address);
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.affiliatedPersons = new ArrayList<>();
    }

    /**
     * Sets the affiliated persons list.
     * @param affiliatedPersons Affiliated persons list
     */
    public void setAffiliatedPersons(List<Person> affiliatedPersons) {
        this.affiliatedPersons = affiliatedPersons;
    }

    /**
     * Adds a person to the affiliated persons list.
     * @param person Person to be added
     */
    public void addAffiliatedPerson(Person person) {
        affiliatedPersons.add(person);
    }

    /**
     * Removes a person from the affiliated persons list.
     * @param person Person to be removed
     */
    public void removeAffiliatedPerson(Person person) {
        affiliatedPersons.remove(person);
    }

    /**
     * Returns an affiliated persons list.
     * @return Affiliated persons list
     */
    public List<Person> getAffiliatedPersons() {
        return affiliatedPersons;
    }

    /**
     * Returns the name of the company.
     * @return Name of company
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the industry of the company.
     * @return Industry of company
     */
    public String getIndustry() {
        return industry;
    }

    /**
     * Returns the location of the company.
     * @return Location of company
     */
    public String getLocation() {
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
    public String getWebsite() {
        return website;
    }

    /**
     * Returns the email of the company.
     * @return Email of company
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns the phone of the company.
     * @return Phone of company
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Returns the address of the company.
     * @return Address of company
     */
    public String getAddress() {
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
                && otherCompany.getAddress().equals(getAddress());
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

        return otherCompany != null
                && otherCompany.getName().equals(getName());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("industry", industry)
                .add("location", location)
                .add("description", description)
                .add("website", website)
                .toString();
    }
}
