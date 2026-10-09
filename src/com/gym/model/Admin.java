package com.gym.model;

public class Admin extends Person {
    public Admin(String id, String name, String email, String contactNumber) {
        super(id, name, email, contactNumber);
    }

    @Override
    public String getRole() { return "Admin"; }
}
