package com.bl.addressbook;

import java.util.*;
import java.util.stream.Collectors;

public class AddressBookSystem {
    private final Map<String, AddressBook> books = new HashMap<>();

    public boolean createAddressBook(String name) {
        String key = safeKey(name);
        if (key.isEmpty() || books.containsKey(key)) return false;
        books.put(key, new AddressBook(name.trim()));
        return true;
    }

    public Optional<AddressBook> getAddressBook(String name) {
        return Optional.ofNullable(books.get(safeKey(name)));
    }

    public Set<String> listAddressBooks() {
        return new TreeSet<>(books.keySet());
    }

    // UC7
    public List<Contact> searchAcrossBooksByCity(String city) {
        String cityKey = safeLower(city);
        return books.values().stream()
                .flatMap(book -> book.getAllContacts().stream())
                .filter(c -> safeLower(c.getCity()).equals(cityKey))
                .collect(Collectors.toList());
    }

    public List<Contact> searchAcrossBooksByState(String state) {
        String stateKey = safeLower(state);
        return books.values().stream()
                .flatMap(book -> book.getAllContacts().stream())
                .filter(c -> safeLower(c.getState()).equals(stateKey))
                .collect(Collectors.toList());
    }

    // ---------------- UC8 (Streams + Map): view persons by City/State ----------------

    public Map<String, List<Contact>> cityToPersonsMap() {
        return books.values().stream()
                .flatMap(book -> book.getAllContacts().stream())
                .collect(Collectors.groupingBy(c -> safeLower(c.getCity())));
    }

    public Map<String, List<Contact>> stateToPersonsMap() {
        return books.values().stream()
                .flatMap(book -> book.getAllContacts().stream())
                .collect(Collectors.groupingBy(c -> safeLower(c.getState())));
    }

    private static String safeKey(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }

    private static String safeLower(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }
}