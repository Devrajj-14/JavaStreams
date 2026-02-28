package com.bl.addressbook;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AddressBook {
    private final String name;
    private final List<Contact> contacts = new ArrayList<>();

    public AddressBook(String name) {
        this.name = name == null ? "" : name.trim();
    }

    public String getName() { return name; }

    public List<Contact> getAllContacts() { return contacts; }

    // UC6: duplicate prevention using streams
    public boolean addContact(Contact contact) {
        if (contact == null) return false;

        boolean duplicate = contacts.stream().anyMatch(existing -> existing.equals(contact));
        if (duplicate) return false;

        contacts.add(contact);
        return true;
    }

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

    // ---------------- UC10 (Streams): sort alphabetically by person's name ----------------
    public List<Contact> sortedByName() {
        return contacts.stream()
                .sorted(Comparator
                        .comparing((Contact c) -> c.getFirstName().toLowerCase())
                        .thenComparing(c -> c.getLastName().toLowerCase()))
                .collect(Collectors.toList());
    }
}