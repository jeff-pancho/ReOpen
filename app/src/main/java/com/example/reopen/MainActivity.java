package com.example.reopen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    List<BusinessListing> listings = new ArrayList<>();
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRef = FirebaseDatabase.getInstance().getReference();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initSearchBar();
        initListings();
    }

    public void initSearchBar() {
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = findViewById(R.id.search_field);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(false);
    }

    public void initListings() {
        mRef.child("listings").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listings.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    BusinessListing bus = snapshot.getValue(BusinessListing.class);
                    listings.add(bus);
                }

                initRecyclerView(R.id.featured_recycler, selectRandomListings(4));
                initRecyclerView(R.id.restaurant_recycler, getBusByCategory("Restaurant"));
                initRecyclerView(R.id.shopping_recycler, getBusByCategory("Shopping"));
                initRecyclerView(R.id.services_recycler, getBusByCategory("Services"));
                initRecyclerView(R.id.other_recycler, getBusByCategory("Other"));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void initRecyclerView(int id, List<BusinessListing> businesses) {
        RecyclerView recyclerView = findViewById(id);
        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(businesses);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);

        adapter.setListener(bus -> {
            Intent i = new Intent(MainActivity.this, BusProfileActivity.class);
            Gson gson = new Gson();
            String busData = gson.toJson(bus);
            i.putExtra("busData", busData);
            startActivity(i);
        });

        recyclerView.setLayoutManager(lm);
    }

    private List<BusinessListing> getBusByCategory(String category) {
        List<BusinessListing> result = new ArrayList<>();
        for (BusinessListing bus : listings) {
            if (bus.getCategory().equals(category)) {
                result.add(bus);
            }
        }
        return result;
    }

    private List<BusinessListing> selectRandomListings(int amount) {
        Random rd = new Random();

        // Shallow copy that we will randomly select from
        List<BusinessListing> clonedListings = new ArrayList<>(listings);
        List<BusinessListing> randomListings = new ArrayList<>();

        for (int i = 0; i < amount && clonedListings.size() > 0; i++) {
            int randIndex = rd.nextInt(clonedListings.size());
            randomListings.add(clonedListings.get(randIndex));
            clonedListings.remove(randIndex);
        }

        return randomListings;
    }

    public void onMakePost(View v) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}