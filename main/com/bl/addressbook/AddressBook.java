package com.bl.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private final List<Contact> contacts = new ArrayList<>();

    // UC1/UC4: add contact
    public void addContact(Contact contact) {
        if (contact != null) {
            contacts.add(contact);
        }
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    // UC2: edit by name
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

    // UC3: delete by name
    public boolean deleteContact(String firstName, String lastName) {
        String key = (firstName + " " + lastName).trim().toLowerCase();
        return contacts.removeIf(c -> c.fullNameKey().equals(key));
    }

    // helper
    public Contact findByName(String firstName, String lastName) {
        String key = (firstName + " " + lastName).trim().toLowerCase();
        for (Contact c : contacts) {
            if (c.fullNameKey().equals(key)) {
                return c;
            }
        }
        return null;
    }
}