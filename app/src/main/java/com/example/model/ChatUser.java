package com.example.model;

public class ChatUser {
    private int role;

    public ChatUser(int role) {
        this.role = role;
    }

    public ChatUser() {
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
