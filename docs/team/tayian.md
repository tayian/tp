---
layout: default.md
title: Ian Tay's Project Portfolio Page
---

### Project: Connectify

Connectify is a networking platform designed to help professionals efficiently manage their connections and enhance their networking experiences. The user interacts with it using a CLI, and it also has a GUI. It is written in Java.

Given below are my contributions to the project.

* **New Feature**: Created attributes to be extended by Company and Person Class
  * Created and organised the attributes: Address, Email, Industry, Location, Name, Phone, Website.
  * Extended the attributes to create PersonAddress, CompanyAddress, PersonEmail, CompanyEmail, etc. classes.
  * Added test cases for the Company and Person class to verify that the attributes are working as intended.
  * Significance: Refactored the Company class according to OOP principles.

* **New Feature**: Implemented `sharePerson` Command
  * Implemented the `sharePerson` command, enabling users to retrieve instructions on how to add a particular Person.
  * This feature allows users to share these people with others, allowing effective network management through creating second- and third-degree connections.
  * Significance: Enhanced the platform's utility for managing relationships with other professionals.

* **New Feature**: Implemented `shareCompany` Command
  * Developed the `shareCompany` command, allowing users to add notes to their company contacts.
  * This feature allows users to share these companies with others, allowing effective network management through disseminating information about companies, gleaned through interaction with their employees.
  * Significance: Enhanced the platform's utility for managing professional relationships with organizations.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2324s1.github.io/tp-dashboard/?search=T15-4&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code&since=2023-09-22&tabOpen=true&tabType=authorship&zFR=false&tabAuthor=tayian&tabRepo=AY2324S1-CS2103T-T15-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Resolved merge conflicts that arose due to parallel editing of similar features.
    * Identified bugs in code and informed team members.

* **Documentation**:
    * User Guide:
        * Updated documentation for Introduction, Command Glossary
        * Updated documentation for User Guide to inform expected behavior for `addCompany` commands.
    * Developer Guide:
        * Updated documentation for User Guide to include Appendix: Instructions for Manual Testing.
        * Added diagrams for the `addCompany`, `deleteCompany` command.

* **Team-Based Tasks**:
    * **Enhanced Code Robustness through Gradle Assertions**: Consistently ensured a maintaining, or increase in code coverage, for all code I have directly implemented, to bolster code robustness and catch possible unexpected behavior.

* **Tools**:
    * **Leveraged PlantUML for Diagram Creation**: Utilized PlantUML to generate meaningful diagrams for the Developer Guide, making it more visually informative for future Developers.
    * **Used CodeCov for Code Coverage**: Utilized CodeCov to track code coverage and ensure that the project meets the minimum code coverage requirements.
