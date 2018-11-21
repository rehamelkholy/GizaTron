package com.example.android.gizatron;

import java.util.ArrayList;
import java.util.Collections;

public class LocationSorter {
    private ArrayList<Location> locations;

    public LocationSorter(ArrayList<Location> locations) {
        this.locations = locations;
    }

    public ArrayList<Location> getSortedLocations() {
        Collections.sort(locations, Location.nameComparator);
        return locations;
    }
}
