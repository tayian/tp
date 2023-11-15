---
layout: default.md
title: Lim Wei Hern's Project Portfolio Page
---

### Project: Connectify

Connectify is a networking platform designed to help professionals efficiently manage their connections and enhance their networking experiences.

Given below are my contributions to the project. A full list of the PRs that I have contributed to can be found [here](https://github.com/AY2324S1-CS2103T-T15-4/tp/pulls?q=is%3Apr+is%3Aclosed+author%3AnreHieW).

* **New Feature**: In charge of the entire UI component [\#110](https://github.com/AY2324S1-CS2103T-T15-4/tp/issues/110) [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/119)
* Created and implemented the UI component for the entire project.
* Learned and familiarized myself with JavaFX  to create the UI.
* Significance: The UI is a fundamental component of the project, as it allows users to interact with the project.

* **New Feature**: Implemented `PersonPriority` [\#80](https://github.com/AY2324S1-CS2103T-T15-4/tp/issues/80) [(Link to Pr)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/92)
* Added a `PersonPriority` class to the project. by creating a `Priority `abstract class.
* This feature allows users to prioritize their contacts and supports the `rank` command.
* Significance: Priority is a fundamental feature of Connectify, as it allows users to prioritize their contacts.

* **New Feature**: Implemented `list`, `people` and `companies` [\#47](https://github.com/AY2324S1-CS2103T-T15-4/tp/issues/47) [\#55](https://github.com/AY2324S1-CS2103T-T15-4/tp/issues/55) [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/58)
* Added the `list`, `people` and `companies` commands to Connectify.
* Added the relevant tests for the commands and support in the UI for auto switching to the relevant tab. .
* Significance: The commands are a fundamental component of the project, as they allow users to view the different categories of contacts.

* **New Feature**: Implemented `editPerson` [\#69](https://github.com/AY2324S1-CS2103T-T15-4/tp/issues/69) [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/76)
* Added the `editPerson` command to Connectify.
* This was done using by also creating the `editPersonDescriptor` class which allows users to edit the details of their contacts within the `personList` of a `company`.
* Significance: This command allows users to edit the details of their contacts.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=T15-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&tabAuthor=nreHieW&tabRepo=AY2324S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Handled issue creation on GitHub for certain weeks.
    * Conducted prompt and efficient issue resolution and pull request reviews.

* **Enhancements to existing features**: Implemented the 2 way association between `Person` and `Company` [\#112](https://github.com/AY2324S1-CS2103T-T15-4/tp/issues/112) [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/119)
* Implemented the 2 way association between `Person` and `Company`. Not only does a Company now have a list of `Person`, but a `Person` also has a reference to the `Company` they are associated with.
* Cleaned up the associated tests and command to support the new association.
* Significance: The association models the fundamental relationship between a `Person` and a `Company`, thereby allowing all the commands to behave in a more intuitive manner.

* **Enhancements to existing features**: Cleaned up the entire testing suite [\#114](https://github.com/AY2324S1-CS2103T-T15-4/tp/issues/114) [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/119)
* Cleaned up the entire testing suite, especially the storage tests.
* Significance: Testing is a fundamental component of the Project and is also a key learning outcome of CS2103T.

* **Team-Based Tasks**:
    * **Established the Project Notes Google Drive**: Set up a Google Drive dedicated to Project Notes, facilitating streamlined team collaboration and centralized project documentation. Play a significant role in maintaining the document and made most of the changing including features demo in every iteration.
    * **Enhanced Code Robustness through Gradle Assertions**: Implemented assertions in Gradle to bolster code robustness, contributing to the software's overall reliability.

* **Review and Mentoring Contributions**:
  * **Provided feedback for team members' PRs**: Provided feedback for team members' PRs, ensuring that the team's codebase is of high quality.
  * **Provided suggestions on how to improve testing suite**: Provided suggestions on how to improve the testing suite, ensuring that the team's codebase is of high quality and consistent with the project's coding standards.

* **Tools**:
    * **Managed SourceTree, Git, and GitHub for Version Control**: Utilized SourceTree, Git, and GitHub for revision control and code management, ensuring efficient development workflows.
    * **Used PlantUML for Diagram Creation**: Utilized PlantUML to generate diagrams for the Developer Guide so that it is more visually informative.
    * **Used CodeCov for Code Coverage**: Utilized CodeCov to track code coverage and ensure that the project meets the minimum code coverage requirements.

* **Documentation**:
    * Contributions to User Guide:
        * Added initial draft version of the User Guide. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/19)
        * Contributed to the standardization of the User Guide. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/99)
        * Updated the FAQs to handle commonly asked questions as a result of user testing. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/188)
        * Add `list`, `people` and `companies` to the User Guide. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/86)
    * Contributions to Developer Guide:
        * Updated the implementation details of the UI and its various components. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/86)
        * Updated the implementation details of the `Entity`, `Person` and `Company` classes. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/86)
        * Created the PlantUML diagrams for the `Entity`, `Person` and `Company` classes. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/86)
        * Created PlantUML diagrams for `ListCompanies`, `ListPeople` and `shareCompany`. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/200)
    * General*
        * Migrated documentation to Markbind. [(Link to PR)](https://github.com/AY2324S1-CS2103T-T15-4/tp/pull/103)
