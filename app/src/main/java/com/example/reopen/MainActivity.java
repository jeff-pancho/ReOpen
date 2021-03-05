package com.example.reopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hide the default toolbar to replace it with a custom one
        getSupportActionBar().hide();

        initRecyclerView(R.id.featured_recycler, BusinessCategory.RESTAURANT);
        initRecyclerView(R.id.restaurant_recycler, BusinessCategory.RESTAURANT);
        initRecyclerView(R.id.shopping_recycler, BusinessCategory.SHOPPING);
        initRecyclerView(R.id.services_recycler, BusinessCategory.SERVICES);
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