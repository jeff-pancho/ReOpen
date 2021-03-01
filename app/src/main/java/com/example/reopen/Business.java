package com.example.reopen;

import java.util.HashMap;

public class Business {
    private final String name;
    private final BusinessCategory category;
    private final int imageResourceId;

    public static final HashMap<BusinessCategory, Business[]> businesses = new HashMap<>();
    static {
        businesses.put(BusinessCategory.RESTAURANT, new Business[] {
                new Business("Yummy restaurant", BusinessCategory.RESTAURANT, R.drawable.dummy),
                new Business("Gross restaurant", BusinessCategory.RESTAURANT, R.drawable.dummy),
                new Business("The restaurant", BusinessCategory.RESTAURANT, R.drawable.dummy),
                new Business("Maybe a restaurant", BusinessCategory.RESTAURANT, R.drawable.dummy)
        });
        businesses.put(BusinessCategory.SERVICES, new Business[] {
                new Business("Cool Services", BusinessCategory.SERVICES, R.drawable.dummy),
                new Business("Warm Services", BusinessCategory.SERVICES, R.drawable.dummy),
                new Business("Hot Services", BusinessCategory.SERVICES, R.drawable.dummy),
                new Business("Absolute Zero Services", BusinessCategory.SERVICES, R.drawable.dummy)
        });
        businesses.put(BusinessCategory.SHOPPING, new Business[] {
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
}
