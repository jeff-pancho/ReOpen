package com.example.reopen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

public class BusProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_profile);

        BusinessListing business = getBusiness();

        TextView busName = findViewById(R.id.businessName);
        busName.setText(business.getName());

        TextView busNameLong = findViewById(R.id.infoData1);
        busNameLong.setText(business.getName());

        TextView addressLine = findViewById(R.id.infoData2);
        addressLine.setText(business.getAddress());

        TextView phoneNumber = findViewById(R.id.infoData3);
        phoneNumber.setText(business.getPhone());

        TextView openingDate = findViewById(R.id.infoData4);
        openingDate.setText(business.getOpenDate());
    }

    private BusinessListing getBusiness() {
        Bundle extras = getIntent().getExtras();
        String busData = extras.getString("busData");
        Gson gson = new Gson();
        BusinessListing business = gson.fromJson(busData, BusinessListing.class);
        return business;
    }
}