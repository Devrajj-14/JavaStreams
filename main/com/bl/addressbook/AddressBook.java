package com.bl.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private final String name;
    private final List<Contact> contacts = new ArrayList<>();

    public AddressBook(String name) {
        this.name = name == null ? "" : name.trim();
    }

    public String getName() { return name; }

    public List<Contact> getAllContacts() { return contacts; }

    // UC6: add contact with duplicate prevention using Streams
    public boolean addContact(Contact contact) {
        if (contact == null) return false;

        boolean duplicate = contacts.stream()
                .anyMatch(existing -> existing.equals(contact)); // uses overridden equals()

        if (duplicate) return false;

        contacts.add(contact);
        return true;
    }

    // UC2
    public boolean editContact(String firstName, String lastName,
                               String address, String city, String state,
                               String zip, String phone, String email) {
        Contact c = findByName(firstName, lastName);
        if (c == null) return false;

        c.setAddress(address);
        c.setCity(city);
        c.setState(state);
        c.setZip(zip);
        c.setPhoneNumber(phone);
        c.setEmail(email);
        return true;
    }

    // UC3
    public boolean deleteContact(String firstName, String lastName) {
        String key = (firstName + " " + lastName).trim().toLowerCase();
        return contacts.removeIf(c -> c.fullNameKey().equals(key));
    }

    public Contact findByName(String firstName, String lastName) {
        String key = (firstName + " " + lastName).trim().toLowerCase();
        return contacts.stream()
                .filter(c -> c.fullNameKey().equals(key))
                .findFirst()
                .orElse(null);
    }
}