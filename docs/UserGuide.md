# Connectify User Guide

Connectify is a networking platform designed to help professionals efficiently manage their connections and enhance their networking experiences. In this version (v1.2), we have focused on delivering the essential features for a Minimum Viable Product (MVP): **adding contacts**, **deleting contacts**, and **listing contacts**. This guide will walk you through the core functionality of Connectify.

## Table of Contents
* [Quick start](#quick-start)
* [Features](#features)
  * [Adding Contacts](#adding-contacts)
  * [Deleting Contacts](#deleting-contacts)
  * [Listing Contacts](#listing-contacts)
  * [Exit](#exit)
* [FAQ](#faq)
* [Known issues](#known-issues)
* [Command Summary](#command-summary)

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.
<br><br>
[More Details Coming Soon]
--------------------------------------------------------------------------------------------------------------------

## Features

### Adding Contacts

#### Command: `add`

The **add** command allows you to add new contacts to your Connectify database. Follow the format below to add contacts:

`add n/NAME e/EMAIL [t/TAG]…`

- `n/NAME`: Specify the name of the contact.
- `e/EMAIL`: Add the email address of the contact.
- `t/TAG`: Optionally, you can include tags to categorize your contacts. Tags help you organize your connections efficiently.

**Example:**

To add a contact named John Doe with the email address johndoe@example.com and tag them as "colleague," use the following command:

```
Add n/John Doe e/johndoe@example.com t/colleague
```

**Successful Output:**

```
Got it. I've added this contact:
John Doe
Email:
```

**Unsuccessful Output:**

```
Please provide a valid email address.
```

### Deleting Contacts

#### Command: `delete`

The **delete** command allows you to remove contacts from your Connectify database. To delete a contact, you need to specify the contact's index.

```
delete INDEX
```

- `INDEX`: Provide the index of the contact you want to delete.

**Example:**

To delete the contact named John Doe at index 1, use the following command:
```
delete 1
```

**Successful Output:**

```
Noted. I've removed this contact:
John Doe
Email:
```

**Unsuccessful Output:**

```
Please provide a valid index.
```

### Listing Contacts

#### Command: `list`

The **list** command enables you to view all the contacts you've added to your Connectify database.

```
list
```

**Successful Output:**

```
Here are the contacts in your list:
1. John Doe
Email:
```

**Unsuccessful Output:**

```
That is not a valid command.
```

Running this command will display a list of all your contacts, including their names, email addresses, and any tags you've assigned to them.

### Exit

#### Command: `exit`

This command exits the program.

**Successful Output:**

```
Bye. Hope to see you again soon!
```

**Unsuccessful Output:**

```
That is not a valid command.
```

[More Features Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## FAQ

[Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## Known issues

[Coming Soon]

--------------------------------------------------------------------------------------------------------------------

## Command Summary

Action | Format, Examples
--------|------------------
**Add** | `add n/NAME e/EMAIL [t/TAG]…` <br> e.g., `add n/John Doe
**Delete** | `delete INDEX` <br> e.g., `delete 1`
**List** | `list`
**Exit** | `exit`

