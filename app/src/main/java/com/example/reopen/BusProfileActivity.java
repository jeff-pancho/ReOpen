package com.example.reopen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class BusProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_profile);

        Bundle extras = getIntent().getExtras();
        BusinessCategory category = (BusinessCategory) extras.get("category");
        int position = (int) extras.get("position");
        Business business = Business.businesses.get(category)[position];

        TextView busName = findViewById(R.id.businessName);
        busName.setText(business.getName());
    }
}