package com.example.reopen;

import androidx.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

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
