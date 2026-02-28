# Address Book System (UC1–UC12) — Java (Console + OOP + Collections + Streams)

## Overview
This project is a console-based **Address Book System** built in Java using:
- **OOP** (Contact, AddressBook, AddressBookSystem)
- **Collections** (List, Map)
- **Java Streams** (anyMatch, flatMap, groupingBy, counting, sorted, filter)
- **Git UC-wise branching workflow** (each UC in a separate branch and merged to `master/main`)

---

## Tech Stack
- Java 8+ (Streams)
- IntelliJ IDEA (recommended)
- Git (branching + version history)

---

## Project Structure
src/com/bl/addressbook/
AddressBookMain.java
AddressBookSystem.java
AddressBook.java
Contact.java

---

## How to Run (IntelliJ)
1. Open project in IntelliJ
2. Ensure `src` is marked as **Sources Root**
3. Run: `com.bl.addressbook.AddressBookMain`

---

## Git Workflow (UC-wise)
For each UC:
```bash
git checkout master
git pull
git checkout -b feature/UCx-<short-name>

# implement UC
git add .
git commit -m "UCx: <meaningful message>"

git checkout master
git merge feature/UCx-<short-name>
git push origin master
Recommended commit messages:
START: print welcome message in AddressBookMain
UC1: add new contact via console with Contact and AddressBook
UC2: edit contact by name using console
UC3: delete contact by name using console
UC4: store multiple contacts using List and menu loop
UC5: support multiple address books using map with unique names
UC6: prevent duplicate contact by name using equals and stream anyMatch
UC7: search persons by city/state across multiple address books using streams
UC8: view persons by city/state using maps and streams groupingBy
UC9: count contacts by city/state across address books using streams
UC10: sort contacts alphabetically by name using streams
UC11: sort contacts by city/state/zip using streams and comparator
UC12: finalize menus with thank you and add duplicate report using streams
Use Cases Summary (UC1–UC12)
START (Master)
Prints: "Welcome to Address Book Program" in AddressBookMain.
UC1 — Add a New Contact
Input via console:
firstName, lastName, address, city, state, zip, phoneNumber, email
Create Contact object and add to AddressBook.
UC2 — Edit Existing Contact by Name
Search contact by firstName + lastName
Update address, city, state, zip, phone, email.
UC3 — Delete Contact by Name
Delete contact using firstName + lastName match.
UC4 — Add Multiple Contacts
Store contacts using List<Contact>
Add persons one-by-one from console.
UC5 — Multiple Address Books (Unique Name)
Create multiple address books using:
Map<String, AddressBook> books
Select an address book and perform UC1–UC4 actions.
UC6 — Prevent Duplicate Entry in Same AddressBook
Duplicate check is done on Person Name
Implemented by:
overriding equals() and hashCode() in Contact
using streams anyMatch() in AddressBook.addContact()
If duplicate found: do not add.
UC7 — Search Person by City/State Across Multiple AddressBooks (Streams)
Search across all address books using:
books.values().stream().flatMap(book -> book.getAllContacts().stream())
Filter by city/state and return list.
UC8 — View Persons by City/State (Map + Streams)
Create dictionaries:
city -> persons
state -> persons
Using streams:
Collectors.groupingBy(...)
UC9 — Count by City/State (Streams)
Count contacts grouped by city/state using:
Collectors.groupingBy(..., Collectors.counting())
Output: Map<String, Long>
UC10 — Sort Entries by Name (Streams)
Sort contacts by firstName then lastName using:
sorted(Comparator.comparing(...).thenComparing(...))
UC11 — Sort Entries by City/State/Zip (Streams)
Sort functions:
sortedByCity()
sortedByState()
sortedByZip()
UC12 — Final Polish + Thank You
Exit prints: "Thank You"
Added a review-friendly Streams feature:
Duplicate Names Report using groupingBy + counting
(Even if UC6 blocks duplicates, this demonstrates strong stream knowledge.)
Code Summary (What each class does)
Contact
Stores contact fields (name, address, city, state, zip, phone, email)
Provides fullNameKey() for searching/duplicate check
Overrides toString() for clean printing
UC6: overrides equals() + hashCode() based on name
AddressBook
Represents one address book (has a name + List of contacts)
Supports:
add (UC1/UC4/UC6)
edit (UC2)
delete (UC3)
sort (UC10/UC11)
duplicate report (UC12)
AddressBookSystem
Manages multiple address books using Map
Supports:
create/select/list books (UC5)
search across books (UC7)
view maps by city/state (UC8)
count by city/state (UC9)
AddressBookMain
Console UI
Prints welcome message (START)
Provides menus to execute UC1–UC12
Sample Console Flow
Create AddressBook: Friends
Select AddressBook: Friends
Add contact(s)
Edit / Delete
Search across all books by city/state
View city/state mapping
Count by city/state
Sort by name / city / state / zip
Exit → prints Thank You