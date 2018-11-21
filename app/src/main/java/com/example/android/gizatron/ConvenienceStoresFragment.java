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
public class ConvenienceStoresFragment extends Fragment {

    public ConvenienceStoresFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_list, container, false);

        //Create a list of Convenience Stores locations
        final ArrayList<Location> convenienceStoresLocations = new ArrayList<>();
        addLocationsToList(convenienceStoresLocations);
        //Sort locations according to their names
        LocationSorter locationSorter = new LocationSorter(convenienceStoresLocations);
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
        locationsList.add(new Location(getString(R.string.convenience_store1_name), getString(R.string.convenience_store1_address), getString(R.string.convenience_store), getString(R.string.convenience_store1_description), getString(R.string.convenience_store1_phone_number), 0f, 24f, imageList, getString(R.string.convenience_store1_plus_code)));
        locationsList.add(new Location(getString(R.string.convenience_store2_name), getString(R.string.convenience_store2_address), getString(R.string.convenience_store), getString(R.string.convenience_store), getString(R.string.convenience_store2_phone_number), 8f, 1f, imageList, getString(R.string.convenience_store2_plus_code)));
    }
}
