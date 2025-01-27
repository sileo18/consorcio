package com.example.sileo.enums;

public enum UserRole {

    ADMIN(role:"admin"),
    USER(role:"user");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
