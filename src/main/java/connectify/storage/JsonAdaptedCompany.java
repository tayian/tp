package connectify.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import connectify.commons.exceptions.IllegalValueException;
import connectify.model.company.Company;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyIndustry;
import connectify.model.company.CompanyLocation;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyPhone;
import connectify.model.company.CompanyWebsite;
import connectify.model.person.Person;
import connectify.model.person.PersonList;

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

    private final List<JsonAdaptedPerson> personList = new ArrayList<>();


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
                              @JsonProperty("personList") List<JsonAdaptedPerson> personList) {
        this.name = name;
        this.industry = industry;
        this.location = location;
        this.description = description;
        this.website = website;
        this.email = email;
        this.phone = phone;
        this.address = address;
        if (personList != null) {
            this.personList.addAll(personList);
        }
    }

    /**
     * Converts a given {@code Company} into this class for Jackson use.
     *
     * @param source The source {@code Company} object to convert from.
     */
    public JsonAdaptedCompany(Company source) {
        name = source.getName().fullName;
        industry = source.getIndustry().value;
        location = source.getLocation().value;
        description = source.getDescription();
        website = source.getWebsite().value;
        email = source.getEmail().value;
        phone = source.getPhone().value;
        address = source.getAddress().value;
        personList.addAll(source.getPersonList().asList().stream().map(JsonAdaptedPerson::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted company object into the model's {@code Company} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Company toModelType() throws IllegalValueException {
        final List<Person> personListCompany = new ArrayList<>();
        for (JsonAdaptedPerson person : this.personList) {
            personListCompany.add(person.toModelType());
        }
        final PersonList modelPersonList = PersonList.fromList(personListCompany);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyName.class.getSimpleName()));
        }
        if (!CompanyName.isValidName(name)) {
            throw new IllegalValueException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        final CompanyName modelName = new CompanyName(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyPhone.class.getSimpleName()));
        }
        if (!CompanyPhone.isValidPhone(phone)) {
            throw new IllegalValueException(CompanyPhone.MESSAGE_CONSTRAINTS);
        }
        final CompanyPhone modelCompanyPhone = new CompanyPhone(phone);

        if (industry == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyIndustry.class.getSimpleName()));
        }
        final CompanyIndustry modelCompanyIndustry = new CompanyIndustry(industry);

        if (location == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyLocation.class.getSimpleName()));
        }
        final CompanyLocation modelCompanyLocation = new CompanyLocation(location);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Description"));
        }

        if (website == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyWebsite.class.getSimpleName()));
        }
        final CompanyWebsite modelCompanyWebsite = new CompanyWebsite(website);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyEmail.class.getSimpleName()));
        }
        if (!CompanyEmail.isValidEmail(email)) {
            throw new IllegalValueException(CompanyEmail.MESSAGE_CONSTRAINTS);
        }
        final CompanyEmail modelCompanyEmail = new CompanyEmail(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CompanyAddress.class.getSimpleName()));
        }
        if (!CompanyAddress.isValidAddress(address)) {
            throw new IllegalValueException(CompanyAddress.MESSAGE_CONSTRAINTS);
        }
        final CompanyAddress modelCompanyAddress = new CompanyAddress(address);

        return new Company(modelName, modelCompanyIndustry, modelCompanyLocation,
                description, modelCompanyWebsite, modelCompanyEmail,
                modelCompanyPhone, modelCompanyAddress, modelPersonList);
    }
}
