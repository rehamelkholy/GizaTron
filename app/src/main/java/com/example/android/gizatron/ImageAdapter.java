package com.example.android.gizatron;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {
    private ArrayList<Integer> mImageResourceIds;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    ImageAdapter(Context context, ArrayList<Integer> imageResourceIds) {
        this.mInflater = LayoutInflater.from(context);
        mImageResourceIds = imageResourceIds;
    }

    // inflates the layout from xml when needed
    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = mInflater.inflate(R.layout.image_item, viewGroup, false);
        return new ViewHolder(view);
    }

    // binds the data to the ImageView
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Integer imageResourceId = mImageResourceIds.get(position);
        viewHolder.imageView.setImageResource(imageResourceId);
    }

    @Override
    public int getItemCount() {
        return mImageResourceIds.size();
    }

    // stores and recycles views as they are scrolled off screen
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
        }
    }
}
