package com.project.tghunter;

import org.drinkless.td.libcore.telegram.Client;

public class Profile {
    private String name;

    private int id;

    private boolean isActive;

    private Client client;

    public Profile(String name, int id, boolean isActive, Client client) {
        this.name = name;
        this.id = id;
        this.isActive = isActive;
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public String toString() {
        if (name == null || id == 0) {
            return null;
        }
        return "Profile{name: " + name + ", id: " + String.valueOf(id) + ", isActive: " + Boolean.toString(isActive) + "}";
    }
}
