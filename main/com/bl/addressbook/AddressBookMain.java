package com.bl.addressbook;

import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        AddressBook book = new AddressBook();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("1) Add Contact  2) Edit Contact");
            System.out.print("Choose: ");
            String choice = sc.nextLine().trim();

            if ("1".equals(choice)) {
                Contact c = readContact(sc);
                book.addContact(c);
                System.out.println("Saved: " + book.getContact());
            } else if ("2".equals(choice)) {
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
                System.out.println(ok ? "Updated: " + book.getContact() : "Contact not found.");
            } else {
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