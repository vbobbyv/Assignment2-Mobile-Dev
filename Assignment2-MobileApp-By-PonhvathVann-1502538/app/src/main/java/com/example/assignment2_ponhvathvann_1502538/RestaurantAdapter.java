package com.example.assignment2_ponhvathvann_1502538;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class RestaurantAdapter  extends ArrayAdapter {
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<Restaurant> retrievedRestaurantList;

    public RestaurantAdapter(@NonNull Context context, int resource, List<Restaurant> retrievedRestaurantList) {
        super(context, resource);
        this.layoutResource = resource;
        this.layoutInflater = LayoutInflater.from(context);
        this.retrievedRestaurantList = retrievedRestaurantList;
    }

    @Override
    public int getCount() {
        return retrievedRestaurantList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = layoutInflater.inflate(layoutResource, parent, false);
        TextView tvRestaurantName = view.findViewById(R.id.tvRestaurantName);
        TextView tvCuisine = view.findViewById(R.id.tvCuisine);
        TextView tvAddress = view.findViewById(R.id.tvAddress);
        TextView tvContact = view.findViewById(R.id.tvContact);
        TextView tvAvgCost = view.findViewById(R.id.tvAvgCost);
        TextView tvRatingCount = view.findViewById(R.id.tvRatingCount);
        TextView tvRating = view.findViewById(R.id.tvRating);

        Restaurant currentRestaurant = retrievedRestaurantList.get(position);

        tvRestaurantName.setText(currentRestaurant.getName());
        tvCuisine.setText(currentRestaurant.getCuisine());
        tvAddress.setText("Address: " + currentRestaurant.getAddress());
        tvContact.setText(currentRestaurant.getContact());
        tvAvgCost.setText("Avg Cost for 2: $" + currentRestaurant.getAverageCost());
        tvRating.setText("Rating: " + currentRestaurant.getRating()  + "/5");
        tvRatingCount.setText("Rating Count: " + currentRestaurant.getReviewCount());

        return view;
    }
}
