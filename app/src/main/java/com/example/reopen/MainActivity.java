package com.example.reopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initSearchBar();

        initRecyclerView(R.id.featured_recycler, BusinessCategory.RESTAURANT);
        initRecyclerView(R.id.restaurant_recycler, BusinessCategory.RESTAURANT);
        initRecyclerView(R.id.shopping_recycler, BusinessCategory.SHOPPING);
        initRecyclerView(R.id.services_recycler, BusinessCategory.SERVICES);
    }

    public void initSearchBar() {
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = findViewById(R.id.search_field);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconifiedByDefault(false);
    }

    private void initRecyclerView(int id, BusinessCategory category) {
        RecyclerView recyclerView = findViewById(id);
        CaptionedImagesAdapter adapter =
                new CaptionedImagesAdapter(Business.businesses.get(category));
        recyclerView.setAdapter(adapter);

        adapter.setListener((cat, position) -> {
            Intent i = new Intent(MainActivity.this, BusProfileActivity.class);
            i.putExtra("category", cat);
            i.putExtra("position", position);
            startActivity(i);
        });

        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        lm.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setLayoutManager(lm);
    }
}