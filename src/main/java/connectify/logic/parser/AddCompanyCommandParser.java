package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import connectify.logic.commands.AddCompanyCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.company.Company;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyIndustry;
import connectify.model.company.CompanyLocation;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyPhone;
import connectify.model.company.CompanyWebsite;

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

        argMultimap.verifyNoDuplicatePrefixesFor(CliSyntax.PREFIX_NAME, CliSyntax.PREFIX_PHONE,
                CliSyntax.PREFIX_EMAIL, CliSyntax.PREFIX_ADDRESS, CliSyntax.PREFIX_WEBSITE,
                CliSyntax.PREFIX_INDUSTRY, CliSyntax.PREFIX_LOCATION, CliSyntax.PREFIX_DESCRIPTION);

        CompanyName name = ParserCompanyUtil.parseName(argMultimap.getValue(CliSyntax.PREFIX_NAME).get());
        CompanyIndustry industry = ParserCompanyUtil.parseIndustry(argMultimap
                .getValue(CliSyntax.PREFIX_INDUSTRY).get());
        CompanyLocation location = ParserCompanyUtil.parseLocation(argMultimap
                .getValue(CliSyntax.PREFIX_LOCATION).get());
        String description = argMultimap.getValue(CliSyntax.PREFIX_DESCRIPTION)
                .orElseThrow(() -> new ParseException("Description is required"));
        CompanyWebsite website = ParserCompanyUtil.parseWebsite(argMultimap.getValue(CliSyntax.PREFIX_WEBSITE).get());
        CompanyEmail email = ParserCompanyUtil.parseEmail(argMultimap.getValue(CliSyntax.PREFIX_EMAIL).get());
        CompanyPhone phone = ParserCompanyUtil.parsePhone(argMultimap.getValue(CliSyntax.PREFIX_PHONE).get());
        CompanyAddress address = ParserCompanyUtil.parseAddress(argMultimap.getValue(CliSyntax.PREFIX_ADDRESS).get());

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
