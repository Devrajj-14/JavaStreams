package com.bl.addressbook;

import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Address Book Program");

        try (Scanner sc = new Scanner(System.in)) {
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

            Contact c = new Contact(fn, ln, addr, city, state, zip, phone, email);

            AddressBook book = new AddressBook();
            book.addContact(c);

            System.out.println("Saved Contact:");
            System.out.println(book.getContact());
        }
    }
}