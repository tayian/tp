package connectify.model.company;

import java.util.List;
import java.util.function.Predicate;

import connectify.commons.util.StringUtil;
import connectify.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Company}'s {@code Name} matches any of the keywords given.
 */
public class CompanyNameContainsKeywordsPredicate implements Predicate<Company> {
    private final List<String> keywords;

    public CompanyNameContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Company company) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(company.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyNameContainsKeywordsPredicate)) {
            return false;
        }

        CompanyNameContainsKeywordsPredicate otherNameContainsKeywordsPredicate =
                (CompanyNameContainsKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}
