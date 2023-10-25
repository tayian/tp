package connectify.logic.parser;

import static java.util.Objects.requireNonNull;

import connectify.commons.core.index.Index;
import connectify.commons.util.StringUtil;
import connectify.logic.parser.exceptions.ParseException;
import connectify.model.company.CompanyAddress;
import connectify.model.company.CompanyEmail;
import connectify.model.company.CompanyIndustry;
import connectify.model.company.CompanyLocation;
import connectify.model.company.CompanyName;
import connectify.model.company.CompanyPhone;
import connectify.model.company.CompanyWebsite;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserCompanyUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing
     * whitespaces will be trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static CompanyName parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!CompanyName.isValidName(trimmedName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code Industry}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code industry} is invalid.
     */
    public static CompanyIndustry parseIndustry(String industry) throws ParseException {
        requireNonNull(industry);
        String trimmedName = industry.trim();
        return new CompanyIndustry(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code Location}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code location} is invalid.
     */
    public static CompanyLocation parseLocation(String location) throws ParseException {
        requireNonNull(location);
        String trimmedName = location.trim();
        return new CompanyLocation(trimmedName);
    }

    /**
     * Parses a {@code String name} into a {@code Website}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code website} is invalid.
     */
    public static CompanyWebsite parseWebsite(String website) throws ParseException {
        requireNonNull(website);
        String trimmedName = website.trim();
        return new CompanyWebsite(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static CompanyPhone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!CompanyPhone.isValidPhone(trimmedPhone)) {
            throw new ParseException(CompanyPhone.MESSAGE_CONSTRAINTS);
        }
        return new CompanyPhone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static CompanyAddress parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!CompanyAddress.isValidAddress(trimmedAddress)) {
            throw new ParseException(CompanyAddress.MESSAGE_CONSTRAINTS);
        }
        return new CompanyAddress(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static CompanyEmail parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!CompanyEmail.isValidEmail(trimmedEmail)) {
            throw new ParseException(CompanyEmail.MESSAGE_CONSTRAINTS);
        }
        return new CompanyEmail(trimmedEmail);
    }

}
