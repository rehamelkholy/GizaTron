package com.example.android.gizatron;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<Location> mLocations;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    LocationAdapter(Context context, ArrayList<Location> locations) {
        this.mInflater = LayoutInflater.from(context);
        mLocations = locations;
        mContext = context;
    }

    // inflates the layout from xml when needed
    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.list_item, viewGroup, false);
        return new LocationAdapter.ViewHolder(view);
    }

    // binds the data to the views
    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder viewHolder, int position) {
        Location location = mLocations.get(position);
        //set the adapter of the RecyclerView within the list_item layout
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        ImageAdapter imageAdapter = new ImageAdapter(mContext, location.getImageResourceIds());
        viewHolder.recyclerView.setAdapter(imageAdapter);
        //set the content of the rest of the TextViews in the layout
        viewHolder.nameView.setText(location.getName());
        viewHolder.addressView.setText(location.getAddress());
        //get the current time on the device
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        float currentTime = (float) ((float) calendar.get(Calendar.HOUR_OF_DAY) + (calendar.get(Calendar.MINUTE) / 60.) + (calendar.get(Calendar.SECOND) / 3600.));
        //determine whether the location is Open or Closed and display the status along with auxiliary info
        if ((location.getOpensAt() == 0f) && (location.getClosesAt() == 24f)) {
            viewHolder.statusView.setText(mContext.getString(R.string.open_24_hours));
        } else if ((currentTime > location.getOpensAt()) && (currentTime < location.getClosesAt())) {
            viewHolder.statusView.setText(String.format(mContext.getString(R.string.open_closes_at), formatTime(location.getClosesAt())));
        } else if ((location.getClosesAt() < location.getOpensAt()) && (currentTime < location.getClosesAt())) {
            viewHolder.statusView.setText(String.format(mContext.getString(R.string.open_closes_at), formatTime(location.getClosesAt())));
        } else {
            viewHolder.statusView.setText(String.format(mContext.getString(R.string.closed_opens_at), formatTime(location.getOpensAt())));
        }

    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        RecyclerView recyclerView;
        TextView nameView;
        TextView addressView;
        TextView statusView;

        ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.images_recycler);
            nameView = itemView.findViewById(R.id.name_of_place);
            addressView = itemView.findViewById(R.id.address);
            statusView = itemView.findViewById(R.id.status);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Location getItem(int id) {
        return mLocations.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * A method to format time in hours and minutes
     *
     * @param time is the time as a float variable
     * @return the time formatted as a String variable
     */
    private String formatTime(float time) {
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
