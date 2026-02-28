package com.bl.addressbook;

public class AddressBook {
    private Contact contact; // UC1: single person

    public void addContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }
}