package connectify.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import connectify.commons.core.index.Index;
import connectify.commons.util.ToStringBuilder;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.Model;
import connectify.model.company.Company;

/**
 * Shares a company to another address book.
 */
public class ShareCompanyCommand extends Command {

    public static final String COMMAND_WORD = "shareCompany";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shares instructions on how to add a Company to another address book.\n"
            + "Parameters: "
            + "INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHARE_COMPANY_SUCCESS = "Command to add this Company:\n%1$s\n"
            + "Do take note that you need to add people on your own.";

    private final Index targetIndex;

    /**
     * Creates a ShareCompanyCommand to share the specified {@code Company}
     */
    public ShareCompanyCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> lastShownList = model.getFilteredCompanyList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company companyToShare = lastShownList.get(targetIndex.getZeroBased());
        return new CommandResult(String.format(MESSAGE_SHARE_COMPANY_SUCCESS,
                formatInput(Messages.format(companyToShare))));
    }

    /**
     * Searches for Company information, and formats the output to the user.
     *
     * @param input Input to be formatted.
     * @return Formatted output.
     */
    public static String formatInput(String input) {
        String[] parts = input.split("; ");
        StringBuilder result = new StringBuilder("addCompany ");

        for (String part : parts) {
            String[] keyValue = part.split(": ", 2);
            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";

            switch (key) {
            case "Industry":
                result.append("i/").append(value).append(" ");
                break;
            case "Location":
                result.append("l/").append(value).append(" ");
                break;
            case "Description":
                result.append("d/").append(value).append(" ");
                break;
            case "Website":
                result.append("w/").append(value).append(" ");
                break;
            case "Email":
                result.append("e/").append(value).append(" ");
                break;
            case "Phone":
                result.append("p/").append(value).append(" ");
                break;
            case "Address":
                result.append("a/").append(value).append(" ");
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
