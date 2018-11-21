package com.example.android.gizatron;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ATMsFragment extends Fragment {


    public ATMsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_list, container, false);

        //Create a list of ATM locations
        final ArrayList<Location> atmLocations = new ArrayList<>();
        addLocationsToList(atmLocations);
        //Sort locations according to their names
        LocationSorter locationSorter = new LocationSorter(atmLocations);
        ArrayList<Location> sortedLocations = locationSorter.getSortedLocations();
        //Populate the list of locations using a RecyclerView and a custom adapter
        RecyclerView recyclerView = rootView.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        final LocationAdapter locationAdapter = new LocationAdapter(getActivity(), sortedLocations);
        locationAdapter.setClickListener(new LocationAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), LocationActivity.class);
                intent.putExtra("location", locationAdapter.getItem(position));
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(locationAdapter);

        return rootView;
    }

    void addLocationsToList(ArrayList<Location> locationsList) {
        ArrayList<Integer> imageList = new ArrayList<>();
        imageList.add(R.drawable.placeholder_image);
        imageList.add(R.drawable.placeholder_image);
        locationsList.add(new Location(getString(R.string.atm1_name), getString(R.string.atm1_address), getString(R.string.atm), getString(R.string.atm), "", 0f, 24f, imageList, getString(R.string.atm1_plus_code)));
        locationsList.add(new Location(getString(R.string.atm2_name), getString(R.string.atm2_address), getString(R.string.atm), getString(R.string.atm), getString(R.string.atm2_phone_number), 0f, 24f, imageList, getString(R.string.atm2_plus_code)));
        locationsList.add(new Location(getString(R.string.atm3_name), getString(R.string.atm3_address), getString(R.string.atm), getString(R.string.atm), "", 0f, 24f, imageList, getString(R.string.atm3_plus_code)));
    }
}
