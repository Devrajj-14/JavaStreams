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

    // UC6
    public boolean addContact(Contact contact) {
        if (contact == null) return false;
        boolean duplicate = contacts.stream().anyMatch(existing -> existing.equals(contact));
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

    // UC10
    public List<Contact> sortedByName() {
        return contacts.stream()
                .sorted(Comparator
                        .comparing((Contact c) -> c.getFirstName().toLowerCase())
                        .thenComparing(c -> c.getLastName().toLowerCase()))
                .collect(Collectors.toList());
    }

    // ---------------- UC11 (Streams): sort by City / State / Zip ----------------

    public List<Contact> sortedByCity() {
        return contacts.stream()
                .sorted(Comparator
                        .comparing((Contact c) -> safeLower(c.getCity()))
                        .thenComparing(c -> safeLower(c.getFirstName()))
                        .thenComparing(c -> safeLower(c.getLastName())))
                .collect(Collectors.toList());
    }

    public List<Contact> sortedByState() {
        return contacts.stream()
                .sorted(Comparator
                        .comparing((Contact c) -> safeLower(c.getState()))
                        .thenComparing(c -> safeLower(c.getFirstName()))
                        .thenComparing(c -> safeLower(c.getLastName())))
                .collect(Collectors.toList());
    }

    public List<Contact> sortedByZip() {
        return contacts.stream()
                .sorted(Comparator
                        .comparing((Contact c) -> safe(c.getZip()))
                        .thenComparing(c -> safeLower(c.getFirstName()))
                        .thenComparing(c -> safeLower(c.getLastName())))
                .collect(Collectors.toList());
    }

    private static String safeLower(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }
}