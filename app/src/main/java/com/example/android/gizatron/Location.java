package com.example.android.gizatron;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Comparator;

public class Location implements Parcelable {

    private String mName;
    private String mAddress;
    private String mShortDescription;
    private String mDescription;
    private String mPhoneNumber;
    private float mOpensAt;
    private float mClosesAt;
    private ArrayList<Integer> mImageResourceIds;
    private String mPlusCode = "";

    public Location(String name, String address, String shortDescription, String description, String phoneNumber, float opensAt, float closesAt, ArrayList<Integer> imageResourceIds) {
        mName = name;
        mAddress = address;
        mShortDescription = shortDescription;
        mDescription = description;
        mPhoneNumber = phoneNumber;
        mOpensAt = opensAt;
        mClosesAt = closesAt;
        mImageResourceIds = imageResourceIds;
    }

    public Location(String name, String address, String shortDescription, String description, String phoneNumber, float opensAt, float closesAt, ArrayList<Integer> imageResourceIds, String plusCode) {
        mName = name;
        mAddress = address;
        mShortDescription = shortDescription;
        mDescription = description;
        mPhoneNumber = phoneNumber;
        mOpensAt = opensAt;
        mClosesAt = closesAt;
        mImageResourceIds = imageResourceIds;
        mPlusCode = plusCode;
    }

    @SuppressWarnings("unchecked")
    Location(Parcel in) {
        mName = in.readString();
        mAddress = in.readString();
        mShortDescription = in.readString();
        mDescription = in.readString();
        mPhoneNumber = in.readString();
        mOpensAt = in.readFloat();
        mClosesAt = in.readFloat();
        mImageResourceIds = in.readArrayList(Integer.class.getClassLoader());
        mPlusCode = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public String getName() {
        return mName;
    }

    public String getAddress() {
        return mAddress;
    }

    String getShortDescription() {
        return mShortDescription;
    }

    public String getDescription() {
        return mDescription;
    }

    String getPhoneNumber() {
        return mPhoneNumber;
    }

    float getOpensAt() {
        return mOpensAt;
    }

    float getClosesAt() {
        return mClosesAt;
    }

    ArrayList<Integer> getImageResourceIds() {
        return mImageResourceIds;
    }

    String getPlusCode() {
        return mPlusCode;
    }

    boolean hasPlusCode() {
        return !mPlusCode.isEmpty();
    }

    static Comparator<Location> nameComparator = new Comparator<Location>() {
        @Override
        public int compare(Location location, Location t1) {
            return (location.getName().compareToIgnoreCase(t1.getName()));
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mName);
        parcel.writeString(mAddress);
        parcel.writeString(mShortDescription);
        parcel.writeString(mDescription);
        parcel.writeString(mPhoneNumber);
        parcel.writeFloat(mOpensAt);
        parcel.writeFloat(mClosesAt);
        parcel.writeList(mImageResourceIds);
        parcel.writeString(mPlusCode);
    }
}
