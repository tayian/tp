package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import connectify.commons.core.LogsCenter;
import connectify.logic.commands.AddCompanyCommand;
import connectify.logic.commands.AddPersonCommand;
import connectify.logic.commands.ClearCommand;
import connectify.logic.commands.Command;
import connectify.logic.commands.CompanyNoteCommand;
import connectify.logic.commands.DeleteCompanyCommand;
import connectify.logic.commands.DeletePersonCommand;
import connectify.logic.commands.EditPersonCommand;
import connectify.logic.commands.ExitCommand;
import connectify.logic.commands.FindAllCommand;
import connectify.logic.commands.FindCompaniesCommand;
import connectify.logic.commands.FindPeopleCommand;
import connectify.logic.commands.HelpCommand;
import connectify.logic.commands.ListAllCommand;
import connectify.logic.commands.ListCompaniesCommand;
import connectify.logic.commands.ListPeopleCommand;
import connectify.logic.commands.PersonNoteCommand;
import connectify.logic.commands.RankPersonCommand;
import connectify.logic.commands.ShareCompanyCommand;
import connectify.logic.commands.SharePersonCommand;
import connectify.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class ConnectifyParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(ConnectifyParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddPersonCommand.COMMAND_WORD:
            return new AddPersonCommandParser().parse(arguments);

        case AddCompanyCommand.COMMAND_WORD:
            return new AddCompanyCommandParser().parse(arguments);

        case EditPersonCommand.COMMAND_WORD:
            return new EditPersonCommandParser().parse(arguments);

        case DeletePersonCommand.COMMAND_WORD:
            return new DeletePersonCommandParser().parse(arguments);

        case DeleteCompanyCommand.COMMAND_WORD:
            return new DeleteCompanyCommandParser().parse(arguments);

        case PersonNoteCommand.COMMAND_WORD:
            return new PersonNoteCommandParser().parse(arguments);

        case CompanyNoteCommand.COMMAND_WORD:
            return new CompanyNoteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindAllCommand.COMMAND_WORD:
            return new FindAllCommandParser().parse(arguments);

        case FindPeopleCommand.COMMAND_WORD:
            return new FindPeopleCommandParser().parse(arguments);

        case FindCompaniesCommand.COMMAND_WORD:
            return new FindCompaniesCommandParser().parse(arguments);

        case ShareCompanyCommand.COMMAND_WORD:
            return new ShareCompanyCommandParser().parse(arguments);

        case SharePersonCommand.COMMAND_WORD:
            return new SharePersonCommandParser().parse(arguments);

        case ListCompaniesCommand.COMMAND_WORD:
            return new ListCompaniesCommand();

        case ListPeopleCommand.COMMAND_WORD:
            return new ListPeopleCommand();

        case ListAllCommand.COMMAND_WORD:
            return new ListAllCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case RankPersonCommand.COMMAND_WORD:
            return new RankPersonCommand();

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
