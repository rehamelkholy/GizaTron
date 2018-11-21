package com.example.android.gizatron;


import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
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
public class ApparelFragment extends Fragment {

    public ApparelFragment() {
        // Required empty public constructor
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_location_list, container, false);

        //Create a list of Apparel locations
        final ArrayList<Location> apparelLocations = new ArrayList<>();
        addLocationsToList(apparelLocations);
        //Sort locations according to their names
        LocationSorter locationSorter = new LocationSorter(apparelLocations);
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
        firstImageList.add(R.drawable.apparel_item1_image1);
        firstImageList.add(R.drawable.apparel_item1_image2);
        firstImageList.add(R.drawable.apparel_item1_image3);
        firstImageList.add(R.drawable.apparel_item1_image4);
        firstImageList.add(R.drawable.apparel_item1_image5);
        locationsList.add(new Location(getString(R.string.apparel1_name), getString(R.string.apparel1_address), getString(R.string.clothing_store), getString(R.string.apparel1_description), getString(R.string.apparel1_phone_number), 11.75f, 1f, firstImageList, getString(R.string.apparel1_plus_code)));

        ArrayList<Integer> secondImageList = new ArrayList<>();
        secondImageList.add(R.drawable.placeholder_image);
        locationsList.add(new Location(getString(R.string.apparel2_name), getString(R.string.apparel2_address), getString(R.string.clothing_store), getString(R.string.clothing_store), getString(R.string.apparel2_phone_number), 14f, 24f, secondImageList, getString(R.string.apparel2_plus_code)));

        ArrayList<Integer> thirdImageList = new ArrayList<>();
        thirdImageList.add(R.drawable.apparel_item3_image1);
        thirdImageList.add(R.drawable.apparel_item3_image2);
        thirdImageList.add(R.drawable.apparel_item3_image3);
        thirdImageList.add(R.drawable.apparel_item3_image4);
        locationsList.add(new Location(getString(R.string.apparel3_name), getString(R.string.apparel3_address), getString(R.string.clothing_store), getString(R.string.clothing_store), getString(R.string.apparel3_phone_number), 9f, 23.5f, thirdImageList));
    }
}
