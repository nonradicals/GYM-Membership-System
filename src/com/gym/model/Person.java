package com.gym.model;

public abstract class Person {
    private final String id;
    private final String name;
    private final String email;
    private final String contactNumber;

    protected Person(String id, String name, String email, String contactNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getContactNumber() { return contactNumber; }

    public abstract String getRole();
}
