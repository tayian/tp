package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import connectify.logic.commands.AddCompanyCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.company.Company;

/**
 * Parses input arguments and creates a new AddCompanyCommand object
 */
public class AddCompanyCommandParser implements Parser<AddCompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCompanyCommand
     * and returns an AddCompanyCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCompanyCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                        CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_WEBSITE, CliSyntax.PREFIX_INDUSTRY,
                        CliSyntax.PREFIX_LOCATION, CliSyntax.PREFIX_DESCRIPTION);

        if (!arePrefixesPresent(argMultimap, CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE, CliSyntax.PREFIX_EMAIL,
                CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_WEBSITE, CliSyntax.PREFIX_INDUSTRY,
                CliSyntax.PREFIX_LOCATION,
                CliSyntax.PREFIX_DESCRIPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCompanyCommand.MESSAGE_USAGE));
        }

        String name = argMultimap.getValue(CliSyntax.PREFIX_NAME)
                .orElseThrow(() -> new ParseException("Name is required"));
        String industry = argMultimap.getValue(CliSyntax.PREFIX_INDUSTRY)
                .orElseThrow(() -> new ParseException("Industry is required"));
        String location = argMultimap.getValue(CliSyntax.PREFIX_LOCATION)
                .orElseThrow(() -> new ParseException("Location is required"));
        String description = argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION)
                .orElseThrow(() -> new ParseException("Description is required"));
        String website = argMultimap.getValue(CliSyntax.PREFIX_WEBSITE)
                .orElseThrow(() -> new ParseException("Website is required"));
        String email = argMultimap.getValue(CliSyntax.PREFIX_EMAIL)
                .orElseThrow(() -> new ParseException("Email is required"));
        String phone = argMultimap.getValue(CliSyntax.PREFIX_PHONE)
                .orElseThrow(() -> new ParseException("Phone is required"));
        String address = argMultimap.getValue(CliSyntax.PREFIX_ADDRESS)
                .orElseThrow(() -> new ParseException("Address is required"));

        Company company = new Company(name, industry, location, description, website, email, phone, address);

        return new AddCompanyCommand(company);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}