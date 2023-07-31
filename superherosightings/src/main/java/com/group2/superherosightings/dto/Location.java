package com.group2.superherosightings.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Objects;

public class Location {
    // locationID name description address latitude longitude

    private int locationID;

    @NotBlank(message = "Name must not be blank")
    @Size(max = 50, message = "Name must be fewer than 50 characters")
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Size(max = 255, message = "Description must be fewer than 255 characters")
    private String description;


    @NotBlank(message = "Address must not be blank")
    @Size(max = 100, message = "Address must be fewer than 100 characters")
    private String address;


    @NotBlank(message = "Latitude must not be blank")
    @Size(max = 10, message = "Latitude must be fewer than 10 characters")
    private String latitude;

    @NotBlank(message = "Longitude must not be blank")
    @Size(max = 10, message = "Longitude must be fewer than 50 characters")
    private String longitude;

    // getters and setters

    public int getLocationID() {
        return locationID;
    }

    public void setLocationID(int locationID) {
        this.locationID = locationID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;
        Location location = (Location) o;
        return getLocationID() == location.getLocationID() && Objects.equals(getName(), location.getName()) && Objects.equals(getDescription(), location.getDescription()) && Objects.equals(getAddress(), location.getAddress()) && Objects.equals(getLatitude(), location.getLatitude()) && Objects.equals(getLongitude(), location.getLongitude());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLocationID(), getName(), getDescription(), getAddress(), getLatitude(), getLongitude());
    }
}
