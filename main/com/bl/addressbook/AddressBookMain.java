package com.bl.addressbook;

import java.util.List;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        AddressBook book = new AddressBook();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n--- MENU (UC4) ---");
                System.out.println("1) Add Contact");
                System.out.println("2) Edit Contact");
                System.out.println("3) Delete Contact");
                System.out.println("4) View All Contacts");
                System.out.println("0) Exit");
                System.out.print("Choose: ");

                String choice = sc.nextLine().trim();

                switch (choice) {
                    case "1": {
                        Contact c = readContact(sc);
                        book.addContact(c);
                        System.out.println("Added.");
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
                        if (all.isEmpty()) {
                            System.out.println("No contacts yet.");
                        } else {
                            all.forEach(System.out::println);
                        }
                        break;
                    }
                    case "0":
                        System.out.println("Exit.");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
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