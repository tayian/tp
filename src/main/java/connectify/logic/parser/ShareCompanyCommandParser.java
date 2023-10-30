package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import connectify.commons.core.index.Index;
import connectify.logic.commands.ShareCompanyCommand;
import connectify.logic.parser.exceptions.ParseException;

/**
 * Parses a Company object into a ShareCompanyCommand for execution.
 */
public class ShareCompanyCommandParser implements Parser<ShareCompanyCommand> {

    /**
     * Parses the given {@code Company} object and returns a ShareCompanyCommand object for execution.
     * @throws ParseException if the parsing is unsuccessful
     */
    public ShareCompanyCommand parse(String args) throws ParseException {
        try {
            Index index = ParserCompanyUtil.parseIndex(args);
            return new ShareCompanyCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ShareCompanyCommand.MESSAGE_USAGE), pe);
        }
    }
}
