package com.bl.addressbook;

public class Contact {
    private final String firstName;
    private final String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String lastName, String address, String city, String state,
                   String zip, String phoneNumber, String email) {
        this.firstName = safe(firstName);
        this.lastName = safe(lastName);
        this.address = safe(address);
        this.city = safe(city);
        this.state = safe(state);
        this.zip = safe(zip);
        this.phoneNumber = safe(phoneNumber);
        this.email = safe(email);
    }

    private static String safe(String s) {
        return s == null ? "" : s.trim();
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getAddress() { return address; }
    public String getCity() { return city; }
    public String getState() { return state; }
    public String getZip() { return zip; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getEmail() { return email; }

    public void setAddress(String address) { this.address = safe(address); }
    public void setCity(String city) { this.city = safe(city); }
    public void setState(String state) { this.state = safe(state); }
    public void setZip(String zip) { this.zip = safe(zip); }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = safe(phoneNumber); }
    public void setEmail(String email) { this.email = safe(email); }

    public String fullNameKey() {
        return (firstName + " " + lastName).trim().toLowerCase();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + firstName + " " + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}