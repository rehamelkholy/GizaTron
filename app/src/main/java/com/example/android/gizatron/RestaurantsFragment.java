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
public class RestaurantsFragment extends Fragment {

    public RestaurantsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_list, container, false);

        //Create a list of Restaurants locations
        final ArrayList<Location> restaurantsLocations = new ArrayList<>();
        addLocationsToList(restaurantsLocations);
        //Sort locations according to their names
        LocationSorter locationSorter = new LocationSorter(restaurantsLocations);
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
        ArrayList<Integer> firstImageList = new ArrayList<>();
        firstImageList.add(R.drawable.restaurants_item1_image1);
        firstImageList.add(R.drawable.restaurants_item1_image2);
        firstImageList.add(R.drawable.restaurants_item1_image3);
        firstImageList.add(R.drawable.restaurants_item1_image4);
        firstImageList.add(R.drawable.restaurants_item1_image5);
        locationsList.add(new Location(getString(R.string.restaurants1_name), getString(R.string.restaurants1_address), getString(R.string.restaurant), getString(R.string.restaurants1_description), getString(R.string.restaurants1_phone_number), 12f, 23.5f, firstImageList, getString(R.string.restaurants1_plus_code)));

        ArrayList<Integer> secondImageList = new ArrayList<>();
        secondImageList.add(R.drawable.restaurants_item2_image1);
        secondImageList.add(R.drawable.restaurants_item2_image2);
        locationsList.add(new Location(getString(R.string.restaurants2_name), getString(R.string.restaurants2_address), getString(R.string.restaurant), getString(R.string.restaurants2_description), getString(R.string.restaurants2_phone_number), 0f, 24f, secondImageList, getString(R.string.restaurants2_plus_code)));

        ArrayList<Integer> thirdImageList = new ArrayList<>();
        thirdImageList.add(R.drawable.restaurants_item3_image1);
        thirdImageList.add(R.drawable.restaurants_item3_image2);
        thirdImageList.add(R.drawable.restaurants_item3_image3);
        locationsList.add(new Location(getString(R.string.restaurants3_name), getString(R.string.restaurants3_address), getString(R.string.restaurants3_short_description), getString(R.string.restaurants3_description), getString(R.string.restaurants3_phone_number), 11f, 24f, thirdImageList, getString(R.string.restaurants3_plus_code)));
    }

}
