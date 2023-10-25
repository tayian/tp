package connectify.logic.parser;

import static connectify.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static connectify.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static connectify.logic.parser.CliSyntax.PREFIX_COMPANY;
import static connectify.logic.parser.CliSyntax.PREFIX_EMAIL;
import static connectify.logic.parser.CliSyntax.PREFIX_PHONE;
import static connectify.logic.parser.CliSyntax.PREFIX_TAG;
import static connectify.logic.parser.CommandParserTestUtil.assertParseFailure;
import static connectify.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static connectify.testutil.TypicalIndexes.INDEX_THIRD_PERSON;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.logic.commands.CommandTestUtil;
import connectify.logic.commands.EditPersonCommand;
import connectify.logic.commands.EditPersonCommand.EditPersonDescriptor;
import connectify.model.person.PersonAddress;
import connectify.model.person.PersonEmail;
import connectify.model.person.PersonName;
import connectify.model.person.PersonPhone;
import connectify.model.tag.Tag;
import connectify.testutil.EditPersonDescriptorBuilder;

public class EditPersonCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditPersonCommand.MESSAGE_USAGE);

    private EditPersonCommandParser parser = new EditPersonCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no company specified
        System.out.println("1 " + CommandTestUtil.VALID_NAME_AMY);
        assertParseFailure(parser, "1 ", EditPersonCommand.MESSAGE_NO_COMPANY_PROVIDED);

        // no index specified
        assertParseFailure(parser,
                INDEX_FIRST_COMPANY.getOneBased() + CommandTestUtil.VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);
        // no field specified
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased(),
                EditPersonCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "" + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased(), MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + CommandTestUtil.NAME_DESC_AMY, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                        + CommandTestUtil.INVALID_NAME_DESC,
                PersonName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                        + CommandTestUtil.INVALID_PHONE_DESC,
                PersonPhone.MESSAGE_CONSTRAINTS); // invalid phone
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                        + CommandTestUtil.INVALID_EMAIL_DESC,
                PersonEmail.MESSAGE_CONSTRAINTS); // invalid email
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                        + CommandTestUtil.INVALID_ADDRESS_DESC,
                PersonAddress.MESSAGE_CONSTRAINTS); // invalid address
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                        + CommandTestUtil.INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid phone followed by valid email
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.EMAIL_DESC_AMY, PersonPhone.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Person} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.TAG_DESC_HUSBAND
                + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.TAG_DESC_FRIEND + TAG_EMPTY
                + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + TAG_EMPTY + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.TAG_DESC_HUSBAND, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1 " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.INVALID_NAME_DESC
                + CommandTestUtil.INVALID_EMAIL_DESC + CommandTestUtil.VALID_ADDRESS_AMY
                + CommandTestUtil.VALID_PHONE_AMY, PersonName.MESSAGE_CONSTRAINTS);    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.PHONE_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.NAME_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withName(CommandTestUtil.VALID_NAME_AMY)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB).withEmail(CommandTestUtil.VALID_EMAIL_AMY)
                .withAddress(CommandTestUtil.VALID_ADDRESS_AMY).withTags(CommandTestUtil.VALID_TAG_HUSBAND,
                        CommandTestUtil.VALID_TAG_FRIEND).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, INDEX_FIRST_COMPANY,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.EMAIL_DESC_AMY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, INDEX_FIRST_COMPANY,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.NAME_DESC_AMY;
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder()
                .withName(CommandTestUtil.VALID_NAME_AMY).build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, INDEX_FIRST_COMPANY,
                descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // phone
        userInput = targetIndex.getOneBased() + " " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.PHONE_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withPhone(CommandTestUtil.VALID_PHONE_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndex, INDEX_FIRST_COMPANY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // email
        userInput = targetIndex.getOneBased() + " " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.EMAIL_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withEmail(CommandTestUtil.VALID_EMAIL_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndex, INDEX_FIRST_COMPANY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // address
        userInput = targetIndex.getOneBased() + " " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.ADDRESS_DESC_AMY;
        descriptor = new EditPersonDescriptorBuilder().withAddress(CommandTestUtil.VALID_ADDRESS_AMY).build();
        expectedCommand = new EditPersonCommand(targetIndex, INDEX_FIRST_COMPANY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + " " + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + CommandTestUtil.TAG_DESC_FRIEND;
        descriptor = new EditPersonDescriptorBuilder().withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
        expectedCommand = new EditPersonCommand(targetIndex, INDEX_FIRST_COMPANY, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.PHONE_DESC_BOB;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.INVALID_PHONE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE));

        // mulltiple valid fields repeated
        userInput = targetIndex.getOneBased() + CommandTestUtil.PHONE_DESC_AMY + CommandTestUtil.ADDRESS_DESC_AMY
                + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND + CommandTestUtil.PHONE_DESC_AMY
                + CommandTestUtil.ADDRESS_DESC_AMY + CommandTestUtil.EMAIL_DESC_AMY + CommandTestUtil.TAG_DESC_FRIEND
                + CommandTestUtil.PHONE_DESC_BOB + CommandTestUtil.ADDRESS_DESC_BOB + CommandTestUtil.EMAIL_DESC_BOB
                + CommandTestUtil.TAG_DESC_HUSBAND;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + CommandTestUtil.INVALID_PHONE_DESC
                + CommandTestUtil.INVALID_ADDRESS_DESC + CommandTestUtil.INVALID_EMAIL_DESC
                + CommandTestUtil.INVALID_PHONE_DESC + CommandTestUtil.INVALID_ADDRESS_DESC
                + CommandTestUtil.INVALID_EMAIL_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " "
                + PREFIX_COMPANY + INDEX_FIRST_COMPANY.getOneBased()
                + TAG_EMPTY;

        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder().withTags().build();
        EditPersonCommand expectedCommand = new EditPersonCommand(targetIndex, INDEX_FIRST_COMPANY,
                descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}
