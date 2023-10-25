package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import connectify.commons.core.index.Index;
import connectify.logic.commands.DeletePersonCommand;
import connectify.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePersonCommand object
 */
public class DeletePersonCommandParser implements Parser<DeletePersonCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePersonCommand
     * and returns a DeletePersonCommand object for execution. The person is removed from the specified company.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public DeletePersonCommand parse(String args) throws ParseException {
        try {
            String[] splitArgs = args.trim().split("\\s+");

            if (splitArgs.length != 2) {
                throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
            }

            Index companyIndex = ParserPersonUtil.parseIndex(splitArgs[0]);
            Index personIndex = ParserCompanyUtil.parseIndex(splitArgs[1]);

            return new DeletePersonCommand(companyIndex, personIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE), pe);
        }
    }

}
