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

        TextView busNameLong = findViewById(R.id.infoData1);
        busNameLong.setText(business.getName());

        TextView addressLine = findViewById(R.id.infoData2);
        addressLine.setText(business.getAddress1());

        TextView phoneNumber = findViewById(R.id.infoData3);
        phoneNumber.setText(business.getPhoneNumber());

        TextView openingDate = findViewById(R.id.infoData4);
        openingDate.setText(business.getOpeningDate());
    }
}