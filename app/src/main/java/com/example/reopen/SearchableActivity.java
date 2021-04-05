package com.example.reopen;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;

public class SearchableActivity extends ListActivity {
    ArrayAdapter<Business> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        if (Intent.ACTION_SEARCH.equals(i.getAction())) {
            String query = i.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    public void doSearch(String query) {
        // Perform a fuzzy search against all businesses and get the top results
        List<BoundExtractedResult<Business>> results =
                FuzzySearch.extractSorted(query, Business.getAllBusinesses(), bus -> bus.getName());
        List<Business> busList = new ArrayList<>();
        for (BoundExtractedResult<Business> result : results) {
            busList.add(result.getReferent());
        }

        adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, busList);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Business bus = adapter.getItem(position);

        Intent i = new Intent(SearchableActivity.this, BusProfileActivity.class);
        i.putExtra("category", bus.getCategory());
        i.putExtra("position", findBusPosition(bus));
        startActivity(i);
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