package connectify.model;

import static connectify.model.Model.PREDICATE_SHOW_ALL_COMPANIES;
import static connectify.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import connectify.commons.core.GuiSettings;
import connectify.testutil.AddressBookBuilder;
import connectify.testutil.Assert;
import connectify.testutil.TypicalCompanies;
import connectify.testutil.TypicalPersons;
import javafx.collections.ObservableList;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new AddressBook(), new AddressBook(modelManager.getAddressBook()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setAddressBookFilePath(Paths.get("address/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setAddressBookFilePath(Paths.get("new/address/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setAddressBookFilePath_nullPath_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.setAddressBookFilePath(null));
    }

    @Test
    public void setAddressBookFilePath_validPath_setsAddressBookFilePath() {
        Path path = Paths.get("address/book/file/path");
        modelManager.setAddressBookFilePath(path);
        assertEquals(path, modelManager.getAddressBookFilePath());
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> modelManager.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        modelManager.addPerson(TypicalPersons.ALICE);
        assertTrue(modelManager.hasPerson(TypicalPersons.ALICE));
    }

    @Test
    public void hasCompany_companyNotInAddressBook_returnsFalse() {
        assertFalse(modelManager.hasCompany(TypicalCompanies.COMPANY_1));
    }

    @Test
    public void hasCompany_companyInAddressBook_returnsTrue() {
        modelManager.addCompany(TypicalCompanies.COMPANY_1);
        assertTrue(modelManager.hasCompany(TypicalCompanies.COMPANY_1));
    }

    @Test
    public void getCurrEntity_people_returnsPeople() {
        try {
            modelManager.setCurrEntity("people");
            assertEquals("people", modelManager.getCurrEntity());
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
    }

    @Test
    public void getCurrEntity_companies_returnsCompanies() {
        try {
            modelManager.setCurrEntity("companies");
            assertEquals("companies", modelManager.getCurrEntity());
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
    }

    @Test
    public void getCurrEntity_all_returnsAll() {
        try {
            modelManager.setCurrEntity("all");
            assertEquals("all", modelManager.getCurrEntity());
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
    }


    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
                -> modelManager.getFilteredPersonList().remove(0));
    }

    @Test
    public void getFilteredCompanyList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
                -> modelManager.getFilteredCompanyList().remove(0));
    }

    @Test
    public void getFilteredEntityList_modifyList_throwsUnsupportedOperationException() {
        Assert.assertThrows(UnsupportedOperationException.class, ()
                -> modelManager.getFilteredEntityList().remove(0));
    }

    @Test
    public void getFilteredEntityList_getPersonList_returnsCorrectList() {
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        try {
            modelManager.setCurrEntity("people");
            assertEquals(modelManager.getFilteredPersonList(), modelManager.getFilteredEntityList());
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
    }

    @Test
    public void getFilteredEntityList_getCompaniesList_returnsCorrectList() {
        modelManager.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        try {
            modelManager.setCurrEntity("companies");
            assertEquals(modelManager.getFilteredCompanyList(), modelManager.getFilteredEntityList());
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
    }

    @Test
    public void getFilteredEntityList_setCurrEntityToPeople_returnsCorrectList() {
        try {
            modelManager.setCurrEntity("people");
            assertEquals(modelManager.getFilteredPersonList(), modelManager.getFilteredEntityList());
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
    }

    @Test
    public void getFilteredEntityList_setCurrEntityToCompanies_returnsCorrectList() {
        try {
            modelManager.setCurrEntity("companies");
            assertEquals(modelManager.getFilteredCompanyList(), modelManager.getFilteredEntityList());
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
    }


    @Test
    public void getFilteredEntityList_getAllList_returnsCorrectList() {
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        ObservableList<?> personList = modelManager.getFilteredEntityList();

        modelManager.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);
        ObservableList<?> companyList = modelManager.getFilteredEntityList();

        modelManager.updateToAllEntities();
        assertEquals(personList.size() + companyList.size(), modelManager.getFilteredEntityList().size());

        // check items
        for (int i = 0; i < companyList.size(); i++) {
            assertEquals(companyList.get(i), modelManager.getFilteredEntityList().get(i));
        }

        for (int i = 0; i < personList.size(); i++) {
            assertEquals(personList.get(i), modelManager.getFilteredEntityList().get(i + companyList.size()));
        }
    }

    @Test
    public void updateToAllEntities_modifyEntityType_setsEntityType() {
        modelManager.updateToAllEntities();
        assertEquals("all", modelManager.getCurrEntity());
    }

    @Test
    public void getCurrEntity_invalidEntityType_throwsInvalidEntityException() {
        Assert.assertThrows(InvalidEntityException.class, () -> modelManager.setCurrEntity("invalid"));
    }

    @Test
    public void getNumberOfEntities_emptyAddressBook_returnsZero() {
        assertEquals(0, modelManager.getNumberOfEntities());
    }

    @Test
    public void getNumberOfEntities_nonEmptyAddressBook_returnsCorrectNumber() {
        try {
            modelManager.addPerson(TypicalPersons.ALICE);
            modelManager.addCompany(TypicalCompanies.COMPANY_1);
            modelManager.setCurrEntity("all");
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
        assertEquals(2, modelManager.getNumberOfEntities());
        modelManager = new ModelManager();
    }

    @Test
    public void getNumberOfPeople_emptyAddressBook_returnsZero() {
        assertEquals(0, modelManager.getNumberOfPeople());
    }

    @Test
    public void getNumberOfPeople_nonEmptyAddressBook_returnsCorrectNumber() {
        modelManager.addPerson(TypicalPersons.ALICE);
        modelManager.addPerson(TypicalPersons.BENSON);
        assertEquals(2, modelManager.getNumberOfPeople());
        modelManager = new ModelManager();
    }

    @Test
    public void getNumberOfCompanies_emptyAddressBook_returnsZero() {
        assertEquals(0, modelManager.getNumberOfCompanies());
    }

    @Test
    public void getNumberOfCompanies_nonEmptyAddressBook_returnsCorrectNumber() {
        modelManager.addCompany(TypicalCompanies.COMPANY_1);
        modelManager.addCompany(TypicalCompanies.COMPANY_2);
        assertEquals(2, modelManager.getNumberOfCompanies());
        modelManager = new ModelManager();
    }

    @Test
    public void isEmpty_emptyAddressBook_returnsTrue() {
        assertTrue(modelManager.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyAddressBook_returnsFalse() {
        modelManager.addPerson(TypicalPersons.ALICE);
        assertFalse(modelManager.isEmpty());
        modelManager = new ModelManager();
    }

    @Test
    public void toString_emptyAddressBook_returnsZero() {
        assertEquals("There are 0 entities in the address book.\n"
                + "There are 0 people in the address book.\n"
                + "There are 0 companies in the address book.\n", modelManager.toString());
    }

    @Test
    public void toString_nonEmptyAddressBook_returnsCorrectNumber() {
        try {
            modelManager.addPerson(TypicalPersons.ALICE);
            modelManager.addCompany(TypicalCompanies.COMPANY_1);
            modelManager.setCurrEntity("all");
        } catch (InvalidEntityException e) {
            throw new AssertionError("InvalidEntityException should not be thrown.");
        }
        assertEquals("There are 2 entities in the address book.\n"
                + "There are 1 people in the address book.\n"
                + "There are 1 companies in the address book.\n", modelManager.toString());
        modelManager = new ModelManager();
    }

    @Test
    public void equals() {
        AddressBook addressBook = new AddressBookBuilder().withPerson(TypicalPersons.ALICE)
                .withPerson(TypicalPersons.BENSON).build();
        AddressBook differentAddressBook = new AddressBook();
        UserPrefs userPrefs = new UserPrefs();

        // Same addressBook and userPrefs values -> returns true
        modelManager = new ModelManager(addressBook, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(addressBook, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // Same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // Null -> returns false
        assertFalse(modelManager.equals(null));

        // Different types -> returns false
        assertFalse(modelManager.equals(5));

        // Different addressBook -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentAddressBook, userPrefs)));

        // Resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        // Resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCompanyList(PREDICATE_SHOW_ALL_COMPANIES);

        // Different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setAddressBookFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(addressBook, differentUserPrefs)));
    }
}
