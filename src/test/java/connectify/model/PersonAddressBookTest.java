package connectify.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import connectify.logic.commands.CommandTestUtil;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.model.person.exceptions.DuplicatePersonException;
import connectify.testutil.Assert;
import connectify.testutil.PersonBuilder;
import connectify.testutil.TypicalPersons;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PersonAddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    /**
     * Tests if resetting the data with a null value throws a NullPointerException.
     */
    @Test
    public void resetData_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    /**
     * Tests if the data in the address book can be reset with a valid data source.
     */
    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = TypicalPersons.getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    /**
     * Tests if resetting the data with duplicate persons throws a DuplicatePersonException.
     */
    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        List<Person> newPersons = Arrays.asList(TypicalPersons.ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        Assert.assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    /**
     * Tests that {@code hasPerson} with a null argument throws a {@code NullPointerException}.
     */
    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    /**
     * Tests that {@code hasPerson} returns false for a person not present in the address book.
     */
    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(TypicalPersons.ALICE));
    }

    /**
     * Tests that {@code hasPerson} returns true for a person present in the address book.
     */
    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(TypicalPersons.ALICE);
        assertTrue(addressBook.hasPerson(TypicalPersons.ALICE));
    }

    /**
     * Tests that {@code hasPerson} returns true for a person with the same identity fields.
     */
    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(TypicalPersons.ALICE);
        Person editedAlice = new PersonBuilder(TypicalPersons.ALICE).withAddress(CommandTestUtil.VALID_ADDRESS_BOB)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND).build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    /**
     * Tests that modifying the list returned by {@code getPersonList} throws an {@code UnsupportedOperationException}.
     */
    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    /**
     * Tests the correct output of the {@code toString} method of {@code AddressBook}.
     */
    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Company> companies = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Company> getCompanyList() {
            return companies;
        }
    }
}
