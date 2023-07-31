package com.group2.superherosightings.dto;

import java.util.Objects;

public class Superpower {

    private int superpowerID;
    private String name;

    public int getSuperpowerID() {
        return superpowerID;
    }

    public void setSuperpowerID(int superpowerID) {
        this.superpowerID = superpowerID;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Superpower that = (Superpower) o;
        return superpowerID == that.superpowerID && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(superpowerID, name);
    }

}
