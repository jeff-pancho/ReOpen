package com.example.reopen;

import android.app.ListActivity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.BoundExtractedResult;

public class SearchableActivity extends ListActivity {
    ArrayAdapter<BusinessListing> adapter;
    List<BusinessListing> listings = new ArrayList<>();

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
        DatabaseReference mRef = FirebaseDatabase.getInstance().getReference();
        mRef.child("listings").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BusinessListing bus = snapshot.getValue(BusinessListing.class);
                    listings.add(bus);
                }

                // Perform a fuzzy search against all businesses and get the top results
                List<BusinessListing> busList = new ArrayList<>();
                List<BoundExtractedResult<BusinessListing>> results =
                        FuzzySearch.extractSorted(query, listings, BusinessListing::getName);
                for (BoundExtractedResult<BusinessListing> result : results) {
                    busList.add(result.getReferent());
                }

                adapter = new ArrayAdapter<>(SearchableActivity.this,
                        android.R.layout.simple_list_item_1,
                        busList);
                setListAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        BusinessListing bus = adapter.getItem(position);

        Intent i = new Intent(SearchableActivity.this, BusProfileActivity.class);
        Gson gson = new Gson();
        String busData = gson.toJson(bus);
        i.putExtra("busData", busData);
        startActivity(i);
    }
}