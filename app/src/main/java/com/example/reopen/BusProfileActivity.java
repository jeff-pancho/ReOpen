package com.example.reopen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

public class BusProfileActivity extends AppCompatActivity {

    FragmentTransaction transaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_profile);

        BusinessListing business = getBusiness();

        ImageView busProfilePic = findViewById(R.id.businessProfilePic);
        Picasso.get().load(business.getImageURL()).into(busProfilePic);

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

        LatLng location = business.getLocation();
        double lat = location.getLatitude();
        double lng = location.getLongitude();

        Bundle bundle = new Bundle();

        FragmentManager manager = getSupportFragmentManager();
        transaction = manager.beginTransaction();

        bundle.putDouble("latitude", lat);
        bundle.putDouble("longitude", lng);
        MapsFragment fragInfo = new MapsFragment();
        fragInfo.setArguments(bundle);
        transaction.replace(R.id.fragment, fragInfo);
        transaction.commit();
    }

    private BusinessListing getBusiness() {
        Bundle extras = getIntent().getExtras();
        String busData = extras.getString("busData");
        Gson gson = new Gson();
        BusinessListing business = gson.fromJson(busData, BusinessListing.class);
        return business;
    }
}