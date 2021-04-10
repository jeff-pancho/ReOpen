package com.example.reopen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    EditText businessName;
    EditText businessAddress;
    EditText businessEmail;
    EditText businessPhone;
    Spinner businessCategory;
    Spinner businessMonthSpinner;
    Spinner businessDaySpinner;
    EditText businessDescription;
    EditText businessImageURL;
    Button addListing;
    AddrToLatLong atll;

    DatabaseReference databaseListings;

    private void addListing(){
        atll = new AddrToLatLong();
        String name = businessName.getText().toString().trim();
        String address = businessAddress.getText().toString().trim();
        LatLng location = atll.getLocationFromAddress(this, address);
        String email = businessEmail.getText().toString().trim();
        String phone = businessPhone.getText().toString().trim();
        String category = businessCategory.getSelectedItem().toString().trim();
        String open_month = businessMonthSpinner.getSelectedItem().toString().trim();
        String open_day = businessDaySpinner.getSelectedItem().toString().trim();
        String openDate =  open_month + " " + open_day;
        String description = businessDescription.getText().toString().trim();
        String imageURL = businessImageURL.getText().toString().trim();


        if (location == null) {
            Toast.makeText(this, "The address you entered is not valid!", Toast.LENGTH_LONG).show();
            return;
        }

        if (open_month.equals("Feb")) {
            if (open_day.equals("30") || open_day.equals("31")) {
                Toast.makeText(this, "Date is not valid.", Toast.LENGTH_LONG).show();
                return;
            }
        }

        if (open_month.equals("Apr") || open_month.equals("Jun")
                || open_month.equals("Sep") || open_month.equals("Nov")) {
            if (open_day.equals("31")) {
                Toast.makeText(this, "Date is not valid.", Toast.LENGTH_LONG).show();
                return;
            }
        }

        if (category.equals("Business Category")) {
            Toast.makeText(this, "Please select a category for your business", Toast.LENGTH_LONG).show();
            return;
        }


        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(address)
                || TextUtils.isEmpty(email) || TextUtils.isEmpty(phone)
                || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Must enter information in all fields.", Toast.LENGTH_LONG).show();
            return;
        }

        if(description.length() > 280) {
            Toast.makeText(this, "Description must be under 280 chars.", Toast.LENGTH_LONG).show();
            return;
        }


        String id = databaseListings.push().getKey();

        com.example.reopen.LatLng passthroughLatLng =
                new com.example.reopen.LatLng(location.latitude, location.longitude);

        BusinessListing bl = new BusinessListing(id, name, email, phone, category, imageURL, address, openDate, description, passthroughLatLng);

        Task setValueTask = databaseListings.child(id).setValue(bl);

        setValueTask.addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(SignUpActivity.this, "Listing has been added!", Toast.LENGTH_LONG).show();

                businessName.setText("");
                businessAddress.setText("");
                businessEmail.setText("");
                businessPhone.setText("");
                businessCategory.setSelection(0);
                businessDescription.setText("");
                businessImageURL.setText("");

                finish();
            }
        });

        setValueTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, "Something went wrong with your post:" + e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        databaseListings = FirebaseDatabase.getInstance().getReference("listings");

        businessName = findViewById(R.id.sign_up_name);
        businessAddress = findViewById(R.id.sign_up_address);
        businessEmail = findViewById(R.id.sign_up_email);
        businessPhone = findViewById(R.id.sign_up_phone);
        businessCategory = findViewById(R.id.category_spinner);
        businessMonthSpinner = findViewById(R.id.month_spinner);
        businessDaySpinner = findViewById(R.id.day_spinner);
        businessDescription = findViewById(R.id.sign_up_description);
        businessImageURL = findViewById(R.id.sign_up_image_url);

        initSpinner();
        initOpeningDateSpinners();

        addListing = findViewById(R.id.sign_up_button);

        addListing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addListing();
            }
        });

    }

    private void initSpinner() {
        // Override our adapter's methods to show hint
        CharSequence[] businessCategories = getResources().getStringArray(R.array.business_categories);
        ArrayAdapter<CharSequence> categoryAdapter =
                new ArrayAdapter<CharSequence>(this, R.layout.spinner_layout, businessCategories) {

            @Override
            public boolean isEnabled(int position) {
                return position != 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                }
                return textView;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);
                if (position == 0) {
                    textView.setTextColor(Color.GRAY);
                }
                return textView;
            }
        };
        categoryAdapter.setDropDownViewResource(R.layout.spinner_layout);
        businessCategory.setAdapter(categoryAdapter);
    }

    private void initOpeningDateSpinners() {
        CharSequence[] months = getResources().getStringArray(R.array.months_spinner_items);
        ArrayAdapter<CharSequence> monthsAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_layout, months);
        monthsAdapter.setDropDownViewResource(R.layout.spinner_layout);
        businessMonthSpinner.setAdapter(monthsAdapter);

        CharSequence[] days = getResources().getStringArray(R.array.days_spinner_items);
        ArrayAdapter<CharSequence> daysAdapter =
                new ArrayAdapter<>(this, R.layout.spinner_layout, days);
        daysAdapter.setDropDownViewResource(R.layout.spinner_layout);
        businessDaySpinner.setAdapter(daysAdapter);
    }
}