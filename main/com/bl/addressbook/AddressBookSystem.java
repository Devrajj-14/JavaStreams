package com.bl.addressbook;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

public class AddressBookSystem {
    private final Map<String, AddressBook> books = new HashMap<>();

    // UC5: create address book with unique name
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
        // sorted names for display
        return new TreeSet<>(books.keySet());
    }

    private static String safeKey(String s) {
        return s == null ? "" : s.trim().toLowerCase();
    }
}