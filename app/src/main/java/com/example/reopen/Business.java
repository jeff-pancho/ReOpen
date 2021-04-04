package com.example.reopen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Business {
    private final String name;
    private final BusinessCategory category;
    private final int imageResourceId;
    private final String address1;
    private final String phoneNumber;
    private final String openingDate;
    private final String info;

    public static final HashMap<BusinessCategory, Business[]> businesses = new HashMap<>();

    static {
        businesses.put(BusinessCategory.RESTAURANT, new Business[]{
                new Business("Yummy restaurant", BusinessCategory.RESTAURANT, R.drawable.dummy),
                new Business("Gross restaurant", BusinessCategory.RESTAURANT, R.drawable.dummy),
                new Business("The restaurant", BusinessCategory.RESTAURANT, R.drawable.dummy),
                new Business("Maybe a restaurant", BusinessCategory.RESTAURANT, R.drawable.dummy)
        });
        businesses.put(BusinessCategory.SERVICES, new Business[]{
                new Business("Cool Services", BusinessCategory.SERVICES, R.drawable.dummy),
                new Business("Warm Services", BusinessCategory.SERVICES, R.drawable.dummy),
                new Business("Hot Services", BusinessCategory.SERVICES, R.drawable.dummy),
                new Business("Absolute Zero Services", BusinessCategory.SERVICES, R.drawable.dummy)
        });
        businesses.put(BusinessCategory.SHOPPING, new Business[]{
                new Business("The Mall", BusinessCategory.SHOPPING, R.drawable.dummy),
                new Business("The Store", BusinessCategory.SHOPPING, R.drawable.dummy),
                new Business("The Shopping Place", BusinessCategory.SHOPPING, R.drawable.dummy),
                new Business("The Stand", BusinessCategory.SHOPPING, R.drawable.dummy)
        });
    }

    public Business(String name, BusinessCategory category, int imageResourceId) {
        this.name = name;
        this.category = category;
        this.imageResourceId = imageResourceId;
        this.address1 = "12345 999th Street";
        this.phoneNumber = "(123) 456-7890";
        this.openingDate = "99/12/31";
        this.info = "We do stuff. Stuff and stuff like that.";
    }

    public static Business[] getAllBusinesses() {
        List<Business> allBusinesses = new ArrayList<>();
        for (Business[] busArray : businesses.values()) {
            Collections.addAll(allBusinesses, busArray);
        }
        return allBusinesses.toArray(new Business[0]);
    }

    public String getName() {
        return this.name;
    }

    public BusinessCategory getCategory() {
        return this.category;
    }

    public int getImageResourceId() {
        return this.imageResourceId;
    }

    public String getAddress1() {
        return address1;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getOpeningDate() {
        return openingDate;
    }

    public String getInfo() {
        return info;
    }
}
