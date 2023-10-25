package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static connectify.logic.parser.CliSyntax.PREFIX_COMPANY;
import static connectify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connectify.logic.parser.CliSyntax.PREFIX_NAME;
import static connectify.logic.parser.CliSyntax.PREFIX_PHONE;
import static connectify.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import connectify.commons.core.index.Index;
import connectify.logic.commands.EditPersonCommand;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditPersonCommand object
 */
public class EditPersonCommandParser implements Parser<EditPersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditPersonCommand
     * and returns an EditPersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditPersonCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_COMPANY, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                            PREFIX_ADDRESS, PREFIX_TAG);

        Index index;

        try {
            index = ParserPersonUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditPersonCommand.MESSAGE_USAGE), pe);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_COMPANY, PREFIX_NAME,
                PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS);

        EditPersonCommand.EditPersonDescriptor editPersonDescriptor = new EditPersonCommand.EditPersonDescriptor();

        Index companyIndex;
        if (argMultimap.getValue(PREFIX_COMPANY).isPresent()) {
            companyIndex = ParserPersonUtil.parseIndex(argMultimap.getValue(PREFIX_COMPANY).get());
        } else {
            throw new ParseException(String.format(EditPersonCommand.MESSAGE_NO_COMPANY_PROVIDED,
                    EditPersonCommand.MESSAGE_USAGE));
        }

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserPersonUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserPersonUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserPersonUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_ADDRESS).isPresent()) {
            editPersonDescriptor.setAddress(ParserPersonUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editPersonDescriptor::setTags);

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditPersonCommand.MESSAGE_NOT_EDITED);
        }

        return new EditPersonCommand(index, companyIndex, editPersonDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserPersonUtil.parseTags(tagSet));
    }

}
