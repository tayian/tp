package connectify.logic.commands;

import static connectify.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import connectify.commons.core.GuiSettings;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.AddressBook;
import connectify.model.Entity;
import connectify.model.Model;
import connectify.model.ReadOnlyAddressBook;
import connectify.model.ReadOnlyUserPrefs;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.testutil.CompanyBuilder;
import javafx.collections.ObservableList;

/**
 * Contains unit tests for AddCompanyCommand.
 */
public class AddCompanyCommandTest {

    @Test
    public void constructor_nullCompany_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCompanyCommand(null));
    }

    @Test
    public void execute_companyAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingCompanyAdded modelStub = new ModelStubAcceptingCompanyAdded();
        Company validCompany = new CompanyBuilder().build();

        CommandResult commandResult = new AddCompanyCommand(validCompany).execute(modelStub);

        assertEquals(String.format(AddCompanyCommand.MESSAGE_SUCCESS, validCompany),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validCompany), modelStub.companiesAdded);
    }

    @Test
    public void execute_duplicateCompany_throwsCommandException() {
        Company validCompany = new CompanyBuilder().build();
        AddCompanyCommand addCompanyCommand = new AddCompanyCommand(validCompany);
        ModelStub modelStub = new ModelStubWithCompany(validCompany);

        assertThrows(CommandException.class,
                AddCompanyCommand.MESSAGE_DUPLICATE_COMPANY, () -> addCompanyCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Company techCorp = new CompanyBuilder().withName("TechCorp").build();
        Company alphaTech = new CompanyBuilder().withName("AlphaTech").build();
        AddCompanyCommand addTechCorpCommand = new AddCompanyCommand(techCorp);
        AddCompanyCommand addAlphaTechCommand = new AddCompanyCommand(alphaTech);

        // same object -> returns true
        assertTrue(addTechCorpCommand.equals(addTechCorpCommand));

        // same values -> returns true
        AddCompanyCommand addTechCorpCommandCopy = new AddCompanyCommand(techCorp);
        assertTrue(addTechCorpCommand.equals(addTechCorpCommandCopy));

        // different types -> returns false
        assertFalse(addTechCorpCommand.equals(1));

        // null -> returns false
        assertFalse(addTechCorpCommand.equals(null));

        // different company -> returns false
        assertFalse(addTechCorpCommand.equals(addAlphaTechCommand));
    }

    private class ModelStub implements Model {
        @Override
        public void addCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Replaces user prefs data with the data in {@code userPrefs}.
         *
         * @param userPrefs
         */
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {

        }

        /**
         * Returns the user prefs.
         */
        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            return null;
        }

        /**
         * Returns the user prefs' GUI settings.
         */
        @Override
        public GuiSettings getGuiSettings() {
            return null;
        }

        /**
         * Sets the user prefs' GUI settings.
         *
         * @param guiSettings
         */
        @Override
        public void setGuiSettings(GuiSettings guiSettings) {

        }

        /**
         * Returns the user prefs' address book file path.
         */
        @Override
        public Path getAddressBookFilePath() {
            return null;
        }

        /**
         * Sets the user prefs' address book file path.
         *
         * @param addressBookFilePath
         */
        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {

        }

        /**
         * Replaces address book data with the data in {@code addressBook}.
         *
         * @param addressBook
         */
        @Override
        public void setAddressBook(ReadOnlyAddressBook addressBook) {

        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(Company target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(Company target, Company editedCompany) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompanyList(Predicate<Company> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<? extends Entity> getFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void updateToAllEntities() {
            throw new AssertionError("This method should not be called.");
        };
        @Override
        public void setCurrEntity(String s) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public String getCurrEntity() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Integer getNumberOfPeople() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Integer getNumberOfCompanies() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Integer getNumberOfEntities() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Boolean isEmpty() {
            throw new AssertionError("This method should not be called.");
        };
    }

    private class ModelStubWithCompany extends ModelStub {
        private final Company company;

        ModelStubWithCompany(Company company) {
            requireNonNull(company);
            this.company = company;
        }

        @Override
        public boolean hasCompany(Company company) {
            requireNonNull(company);
            return this.company.equals(company);
        }
    }

    private class ModelStubAcceptingCompanyAdded extends ModelStub {
        final ArrayList<Company> companiesAdded = new ArrayList<>();

        @Override
        public boolean hasCompany(Company company) {
            requireNonNull(company);
            return companiesAdded.stream().anyMatch(company::equals);
        }

        @Override
        public void addCompany(Company company) {
            requireNonNull(company);
            companiesAdded.add(company);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
}
