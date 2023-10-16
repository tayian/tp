package connectify.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connectify.logic.commands.AddCompanyCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.company.Company;

public class AddCompanyCommandParserTest {

    private AddCompanyCommandParser parser;

    @BeforeEach
    public void setUp() {
        parser = new AddCompanyCommandParser();
    }

    @Test
    public void parse_validInput_success() throws Exception {
        String userInput = " n/Company A p/12345678 e/hello@email.com "
                +
                "a/123 Main St w/www.google.com i/IT l/Singapore d/Leading IT Solutions";
        AddCompanyCommand expectedCommand = new AddCompanyCommand(
                new Company("Company A", "IT", "Singapore",
                        "Leading IT Solutions", "www.google.com",
                        "hello@email.com", "12345678", "123 Main St", new ArrayList<>()));
        assertEquals(parser.parse(userInput), expectedCommand);
    }

    @Test
    public void parse_missingName_throwsParseException() {
        String userInput = " p/12345678 e/hello@email.com a/123 Main St w/www.google.com "
                +
                "i/IT l/Singapore d/Leading IT Solutions";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_missingPhone_throwsParseException() {
        String userInput = " n/Company A e/hello@email.com a/123 Main St w/www.google.com "
                +
                "i/IT l/Singapore d/Leading IT Solutions";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_invalidPrefix_throwsParseException() {
        String userInput = " n/Company A p/12345678 e/hello@email.com a/123 Main St "
                +
                "w/www.google.com x/InvalidPrefix l/Singapore d/Leading IT Solutions";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }

    @Test
    public void parse_noInput_throwsParseException() {
        String userInput = "";
        assertThrows(ParseException.class, () -> parser.parse(userInput));
    }
}
