package com.example.android.gizatron;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LocationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        //set the toolbar of the activity
        Toolbar toolbar = findViewById(R.id.location_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        assert actionbar != null;
        actionbar.setDisplayHomeAsUpEnabled(true);
        //get the parcelable location to retrieve its attributes
        Intent intent = getIntent();
        final Location location = intent.getParcelableExtra("location");
        //set the title of the toolbar according to the name of the location
        setTitle(location.getName());

        /*
         * set contents of different views
         */
        //populate the RecyclerView with images of the location using an adapter
        RecyclerView imagesContainer = findViewById(R.id.images_container);
        imagesContainer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ImageAdapter imageAdapter = new ImageAdapter(this, location.getImageResourceIds());
        imagesContainer.setAdapter(imageAdapter);
        //set the name of the location
        TextView nameView = findViewById(R.id.name_view);
        nameView.setText(location.getName());
        //set the short description of the location
        TextView shortDescriptionView = findViewById(R.id.short_description_view);
        shortDescriptionView.setText(location.getShortDescription());
        //set description view visibility to gone if description and short description are the same, and otherwise set text to description
        TextView descriptionView = findViewById(R.id.description);
        if (location.getShortDescription().compareToIgnoreCase(location.getDescription()) == 0) {
            descriptionView.setVisibility(View.GONE);
        } else {
            descriptionView.setText(location.getDescription());
        }
        //set the address of the location
        TextView addressView = findViewById(R.id.address_view);
        addressView.setText(location.getAddress());
        //set the working hours of the location
        TextView hoursView = findViewById(R.id.hours_view);
        if ((location.getOpensAt() == 0f) && (location.getClosesAt() == 24f)) {
            hoursView.setText(getString(R.string.open_24_hours));
        } else {
            hoursView.setText(String.format(getString(R.string.working_hours), formatTime(location.getOpensAt()), formatTime(location.getClosesAt())));
        }
        //set the phone number of the location
        TextView phoneNumberView = findViewById(R.id.phone_number_view);
        LinearLayout callButton = findViewById(R.id.call_button);
        if (!location.getPhoneNumber().isEmpty()) {
            phoneNumberView.setText(location.getPhoneNumber());
        } else {
            phoneNumberView.setText(getString(R.string.no_phone_number));
            callButton.setVisibility(View.GONE);
        }

        //set the onClickListener to start an implicit map intent to view the location address on the maps app (if available)
        LinearLayout directionsButton = findViewById(R.id.get_directions);
        directionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapIntent;
                if (location.hasPlusCode()) {
                    mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:0,0?q=%s", Uri.encode(location.getPlusCode()))));
                } else {
                    mapIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format("geo:0,0?q=%s", Uri.encode(location.getAddress()))));
                }
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        //set the onClickListener to start an implicit map intent to initiate a phone call to the location
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(String.format("tel:%s", location.getPhoneNumber())));
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(callIntent);
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent parentIntent = NavUtils.getParentActivityIntent(this);
                assert parentIntent != null;
                parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(parentIntent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A method to format time in hours and minutes
     *
     * @param time is the time as a float variable
     * @return the time formatted as a String variable
     */
    String formatTime(float time) {
        String formattedTime;
        if ((int) time > 12) {
            formattedTime = String.valueOf(((int) time) - 12);
        } else if ((int) time == 0) {
            formattedTime = String.valueOf(12);
        } else {
            formattedTime = String.valueOf((int) time);
        }
        if (!(time - (int) time == 0)) {
            formattedTime += ":" + String.valueOf((int) ((time - (int) time) * 60));
        }
        if (((int) time >= 12) && (time < 24f)) {
            formattedTime += " PM";
            return formattedTime;
        } else {
            formattedTime += " AM";
            return formattedTime;
        }
    }
}
