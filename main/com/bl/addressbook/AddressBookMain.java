package com.bl.addressbook;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        AddressBookSystem system = new AddressBookSystem();
        system.createAddressBook("Default"); // so you can start immediately

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n=== SYSTEM MENU (UC5) ===");
                System.out.println("1) Create Address Book");
                System.out.println("2) Select Address Book");
                System.out.println("3) List Address Books");
                System.out.println("0) Exit");
                System.out.print("Choose: ");

                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1":
                        createBook(system, sc);
                        break;
                    case "2":
                        selectBookAndOperate(system, sc);
                        break;
                    case "3":
                        System.out.println("Books: " + system.listAddressBooks());
                        break;
                    case "0":
                        System.out.println("Exit.");
                        return;
                    default:
                        System.out.println("Invalid option.");
                }
            }
        }
    }

    private static void createBook(AddressBookSystem system, Scanner sc) {
        System.out.print("Enter new AddressBook name: ");
        String name = sc.nextLine();
        boolean created = system.createAddressBook(name);
        System.out.println(created ? "Created: " + name : "Failed: empty/duplicate name.");
    }

    private static void selectBookAndOperate(AddressBookSystem system, Scanner sc) {
        System.out.print("Enter AddressBook name: ");
        String name = sc.nextLine();

        AddressBook book = system.getAddressBook(name).orElse(null);
        if (book == null) {
            System.out.println("AddressBook not found.");
            return;
        }

        // UC1–UC4 operations inside selected book
        while (true) {
            System.out.println("\n--- AddressBook: " + book.getName() + " ---");
            System.out.println("1) Add Contact (UC1/UC4)");
            System.out.println("2) Edit Contact (UC2)");
            System.out.println("3) Delete Contact (UC3)");
            System.out.println("4) View All Contacts (UC4)");
            // In System Menu print:
            System.out.println("5) Search Person by City across all AddressBooks (UC7)");
            System.out.println("6) Search Person by State across all AddressBooks (UC7)");
            System.out.println("7) View Persons by City (UC8)");
            System.out.println("8) View Persons by State (UC8)");
            System.out.println("0) Back to System Menu");
            System.out.print("Choose: ");


            String choice = sc.nextLine().trim();

            switch (choice) {
                    case "1": {
                        Contact c = readContact(sc);
                        boolean added = book.addContact(c);
                        System.out.println(added ? "Added." : "Duplicate name! Contact not added (UC6).");
                        break;
                    }
                case "2": {
                    System.out.print("Enter First Name to edit: ");
                    String fn = sc.nextLine();
                    System.out.print("Enter Last Name to edit: ");
                    String ln = sc.nextLine();

                    System.out.println("Enter new details:");
                    System.out.print("Address: ");
                    String addr = sc.nextLine();
                    System.out.print("City: ");
                    String city = sc.nextLine();
                    System.out.print("State: ");
                    String state = sc.nextLine();
                    System.out.print("Zip: ");
                    String zip = sc.nextLine();
                    System.out.print("Phone: ");
                    String phone = sc.nextLine();
                    System.out.print("Email: ");
                    String email = sc.nextLine();

                    boolean ok = book.editContact(fn, ln, addr, city, state, zip, phone, email);
                    System.out.println(ok ? "Updated." : "Contact not found.");
                    break;
                }
                case "3": {
                    System.out.print("Enter First Name to delete: ");
                    String fn = sc.nextLine();
                    System.out.print("Enter Last Name to delete: ");
                    String ln = sc.nextLine();

                    boolean ok = book.deleteContact(fn, ln);
                    System.out.println(ok ? "Deleted." : "Contact not found.");
                    break;
                }
                case "4": {
                    List<Contact> all = book.getAllContacts();
                    if (all.isEmpty()) System.out.println("No contacts yet.");
                    else all.forEach(System.out::println);
                    break;
                }
                // Add these cases in the main switch (system menu switch):

                case "5": {
                    System.out.print("Enter city: ");
                    String city = sc.nextLine();
                    List<Contact> result = system.searchAcrossBooksByCity(city);

                    if (result.isEmpty()) System.out.println("No persons found in city: " + city);
                    else result.forEach(System.out::println);
                    break;
                }
                case "6": {
                    System.out.print("Enter state: ");
                    String state = sc.nextLine();
                    List<Contact> result = system.searchAcrossBooksByState(state);

                    if (result.isEmpty()) System.out.println("No persons found in state: " + state);
                    else result.forEach(System.out::println);
                    break;
                }
                case "7": {
                    Map<String, List<Contact>> map = system.cityToPersonsMap();

                    if (map.isEmpty()) {
                        System.out.println("No contacts in system.");
                        break;
                    }

                    map.forEach((city, persons) -> {
                        System.out.println("\nCity: " + city + " (count=" + persons.size() + ")");
                        persons.forEach(System.out::println);
                    });
                    break;
                }
                case "8": {
                    Map<String, List<Contact>> map = system.stateToPersonsMap();

                    if (map.isEmpty()) {
                        System.out.println("No contacts in system.");
                        break;
                    }

                    map.forEach((state, persons) -> {
                        System.out.println("\nState: " + state + " (count=" + persons.size() + ")");
                        persons.forEach(System.out::println);
                    });
                    break;
                }
                case "0":
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static Contact readContact(Scanner sc) {
        System.out.print("First Name: ");
        String fn = sc.nextLine();
        System.out.print("Last Name: ");
        String ln = sc.nextLine();
        System.out.print("Address: ");
        String addr = sc.nextLine();
        System.out.print("City: ");
        String city = sc.nextLine();
        System.out.print("State: ");
        String state = sc.nextLine();
        System.out.print("Zip: ");
        String zip = sc.nextLine();
        System.out.print("Phone: ");
        String phone = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();

        return new Contact(fn, ln, addr, city, state, zip, phone, email);
    }
}