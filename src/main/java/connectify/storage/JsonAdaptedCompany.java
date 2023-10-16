package connectify.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import connectify.commons.exceptions.IllegalValueException;
import connectify.model.company.Company;
import connectify.model.person.Person;

/**
 * A Jackson-friendly version of {@link Company}.
 * Represents a JSON-serializable version of the {@link Company} model class, which can be
 * converted to and from the model's {@code Company} object.
 */
public class JsonAdaptedCompany {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Company's %s field is missing!";

    private final String name;
    private final String industry;
    private final String location;
    private final String description;
    private final String website;
    private final String email;
    private final String phone;
    private final String address;
    private final List<Person> affiliatedPersons;


    /**
     * Constructs a {@code JsonAdaptedCompany} with the given company details.
     *
     * @param name The name of the company.
     * @param industry The industry type of the company.
     * @param location The location of the company.
     * @param description A description of the company.
     * @param website The website URL of the company.
     * @param email The email address of the company.
     * @param phone The phone number of the company.
     * @param address The physical address of the company.
     */
    @JsonCreator
    public JsonAdaptedCompany(@JsonProperty("name") String name,
                              @JsonProperty("industry") String industry,
                              @JsonProperty("location") String location,
                              @JsonProperty("description") String description,
                              @JsonProperty("website") String website,
                              @JsonProperty("email") String email,
                              @JsonProperty("phone") String phone,
                              @JsonProperty("address") String address,
                              @JsonProperty("address") List<Person> affiliatedPersons) {
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.affiliatedPersons = affiliatedPersons;
    }

    /**
     * Converts a given {@code Company} into this class for Jackson use.
     *
     * @param source The source {@code Company} object to convert from.
     */
    public JsonAdaptedCompany(Company source) {
        name = source.getName();
        industry = source.getIndustry();
        location = source.getLocation();
        description = source.getDescription();
        website = source.getWebsite();
        email = source.getEmail();
        phone = source.getPhone();
        address = source.getAddress();
        affiliatedPersons = source.getAffiliatedPersons();
    }

    /**
     * Converts this Jackson-friendly adapted company object into the model's {@code Company} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Company toModelType() throws IllegalValueException {
        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Name"));
        }
        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Phone"));
        }

        if (industry == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Industry"));
        }

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Location"));
        }

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Description"));
        }

        if (website == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Website"));
        }

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Email"));
        }

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Address"));
        }

        return new Company(name, industry, location, description, website, email, phone, address, affiliatedPersons);
    }
}
