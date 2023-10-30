package connectify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import connectify.commons.core.index.Index;
import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.person.Person;

/**
 * Shares a person to another address book.
 */
public class SharePersonCommand extends Command {

    public static final String COMMAND_WORD = "sharePerson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shares instructions on how to add a Person to another address book.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHARE_PERSON_SUCCESS = "Command to add this Person:\n%1$s\n"
            + "Do take note that you need to specify Company and priority on your own.";

    private final Index targetIndex;

    /**
     * Creates a ShareCompanyCommand to share the specified {@code Company}
     */
    public SharePersonCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShare = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SHARE_PERSON_SUCCESS,
                formatInput(Messages.format(personToShare))));
    }

    /**
     * Searches for Person information, and formats the output to the user.
     *
     * @param input Input to be formatted.
     * @return Formatted output.
     */
    public static String formatInput(String input) {
        String[] parts = input.split("; ");
        StringBuilder result = new StringBuilder("addPerson ");

        for (String part : parts) {
            String[] keyValue = part.split(": ", 2);
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";

            switch (key) {
            case "Phone":
                result.append("p/").append(value).append(" ");
                break;
            case "Email":
                result.append("e/").append(value).append(" ");
                break;
            case "Address":
                result.append("a/").append(value).append(" ");
                break;
            case "Tags":
                String[] tags = value.replace("[", "").replace("]", "").split("]\\[");
                for (String tag : tags) {
                    result.append("t/").append(tag).append(" ");
                }
                break;
            default:
                result.append("n/").append(key).append(" ");
                break;
            }
        }

        return result.toString().trim();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
