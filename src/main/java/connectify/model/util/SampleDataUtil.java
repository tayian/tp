package connectify.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import connectify.model.AddressBook;
import connectify.model.ReadOnlyAddressBook;
import connectify.model.person.Person;
import connectify.model.person.PersonAddress;
import connectify.model.person.PersonEmail;
import connectify.model.person.PersonName;
import connectify.model.person.PersonPhone;
import connectify.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new PersonName("Alex Yeoh"), new PersonPhone("87438807"), new PersonEmail("alexyeoh@example.com"),
                new PersonAddress("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends")),
            new Person(new PersonName("Bernice Yu"), new PersonPhone("99272758"), new PersonEmail("berniceyu@example.com"),
                new PersonAddress("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends")),
            new Person(new PersonName("Charlotte Oliveiro"), new PersonPhone("93210283"), new PersonEmail("charlotte@example.com"),
                new PersonAddress("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours")),
            new Person(new PersonName("David Li"), new PersonPhone("91031282"), new PersonEmail("lidavid@example.com"),
                new PersonAddress("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family")),
            new Person(new PersonName("Irfan Ibrahim"), new PersonPhone("92492021"), new PersonEmail("irfan@example.com"),
                new PersonAddress("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates")),
            new Person(new PersonName("Roy Balakrishnan"), new PersonPhone("92624417"), new PersonEmail("royb@example.com"),
                new PersonAddress("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
