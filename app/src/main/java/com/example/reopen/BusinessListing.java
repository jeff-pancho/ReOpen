package com.example.reopen;

/**
 * This is a re-write of the Business class, with more information, and without enums so that
 * it's more database friendly.
 */
public class BusinessListing {

    private final String listingID;
    private final String name;
    private final String category;
    private final String email;
    private final String phone;
    private final String imageURL;
    private final String description;
    private final String address;
    private final String openDate;

    public BusinessListing(String listingID, String name, String email, String phone, String category, String imageURL, String address, String openDate, String description) {
        this.listingID = listingID;
        this.name = name;
        this.category = category;
        this.imageURL = imageURL;
        this.address = address;
        this.openDate = openDate;
        this.description = description;
        this.email = email;
        this.phone = phone;
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
}
