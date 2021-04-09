package com.example.reopen;

/**
 * This is a re-write of the Business class, with more information, and without enums so that
 * it's more database friendly.
 */
public class BusinessListing {

    private String listingID;
    private String name;
    private String category;
    private String email;
    private String phone;
    private String imageURL;
    private String description;
    private String address;
    private String openDate;

    private LatLng location;

    public BusinessListing() {

    }

    public BusinessListing(String listingID, String name, String email, String phone, String category, String imageURL, String address, String openDate, String description, LatLng location) {
        this.listingID = listingID;
        this.name = name;
        this.category = category;
        this.imageURL = imageURL;
        this.address = address;
        this.openDate = openDate;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.location = location;
    }

    public LatLng getLocation() {
        return location;
    }

    public String getListingID() {
        return listingID;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getOpenDate() {
        return openDate;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String toString() {
        return this.getName();
    }
}
