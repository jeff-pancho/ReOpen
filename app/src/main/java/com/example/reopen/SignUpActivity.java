package com.example.reopen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

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
    EditText businessOpenDate;
    EditText businessDescription;
    EditText businessImageURL;
    Button addListing;

    DatabaseReference databaseListings;

    private void addListing(){
        String name = businessName.getText().toString().trim();
        String address = businessAddress.getText().toString().trim();
        String email = businessEmail.getText().toString().trim();
        String phone = businessPhone.getText().toString().trim();
        String category = businessCategory.getSelectedItem().toString().trim();
        String openDate = businessOpenDate.getText().toString().trim();
        String description = businessDescription.getText().toString().trim();
        String imageURL = businessImageURL.getText().toString().trim();

        String id = databaseListings.push().getKey();

        BusinessListing bl = new BusinessListing(id, name, email, phone, category, imageURL, address, openDate, description);

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
                businessOpenDate.setText("");
                businessDescription.setText("");
                businessImageURL.setText("");
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
        businessOpenDate = findViewById(R.id.sign_up_date);
        businessDescription = findViewById(R.id.sign_up_description);
        businessImageURL = findViewById(R.id.sign_up_image_url);

        addListing = findViewById(R.id.sign_up_button);

        addListing.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                addListing();
            }
        });

    }
}