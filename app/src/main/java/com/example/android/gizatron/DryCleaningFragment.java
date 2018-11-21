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
public class DryCleaningFragment extends Fragment {

    public DryCleaningFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_list, container, false);

        //Create a list of Dry Cleaning locations
        final ArrayList<Location> dryCleaningLocations = new ArrayList<>();
        addLocationsToList(dryCleaningLocations);
        //Sort locations according to their names
        LocationSorter locationSorter = new LocationSorter(dryCleaningLocations);
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
        locationsList.add(new Location(getString(R.string.drycleaning1_name), getString(R.string.drycleaning1_address), getString(R.string.dry_cleaning), getString(R.string.dry_cleaning), getString(R.string.drycleaning1_phone_number), 11f, 22.5f, imageList, getString(R.string.drycleaning1_plus_code)));
        locationsList.add(new Location(getString(R.string.drycleaning2_name), getString(R.string.drycleaning2_address), getString(R.string.dry_cleaning), getString(R.string.dry_cleaning), getString(R.string.drycleaning2_phone_number), 10f, 22f, imageList, getString(R.string.drycleaning2_plus_code)));
    }
}
