package com.bl.addressbook;

public class AddressBook {
    private Contact contact;

    public void addContact(Contact contact) {
        this.contact = contact;
    }

    public Contact getContact() {
        return contact;
    }

    public boolean editContact(String firstName, String lastName,
                               String address, String city, String state,
                               String zip, String phone, String email) {
        if (contact == null) return false;
        String key = (firstName + " " + lastName).trim().toLowerCase();
        if (!contact.fullNameKey().equals(key)) return false;

        contact.setAddress(address);
        contact.setCity(city);
        contact.setState(state);
        contact.setZip(zip);
        contact.setPhoneNumber(phone);
        contact.setEmail(email);
        return true;
    }
}