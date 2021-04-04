package com.example.reopen;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;

public class SearchableActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        if (Intent.ACTION_SEARCH.equals(i.getAction())) {
            String query = i.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    // All of this is terrible, reimplement after databases are implemented
    public void doSearch(String query) {
        Business[] businesses = Business.getAllBusinesses();
        for (Business bus : businesses) {
            if (query.equals(bus.getName())) {
                Intent i = new Intent(SearchableActivity.this, BusProfileActivity.class);
                i.putExtra("category", bus.getCategory());
                i.putExtra("position", findBusPosition(bus));
                startActivity(i);
            }
        }
        finish();
    }

    public int findBusPosition(Business bus) {
        Business[] businesses = Business.businesses.get(bus.getCategory());
        for (int i = 0; i < businesses.length; i++) {
            if (bus == businesses[i]) {
                return i;
            }
        }
        return -1;
    }
}