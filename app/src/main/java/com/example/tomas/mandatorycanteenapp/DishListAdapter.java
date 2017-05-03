package com.example.tomas.mandatorycanteenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tomas on 21-04-2017.
 */

public class DishListAdapter extends ArrayAdapter<Dish> {
    private int resource;

    public DishListAdapter(Context context, int resource, List<Dish> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dish dish = getItem(position);
        String title = dish.getTitle();
        String description = dish.getDescription();
        LinearLayout dishView;
        if (convertView == null) {
            dishView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, dishView, true);
        } else {
            dishView = (LinearLayout) convertView;
        }
        TextView titleView = (TextView) dishView.findViewById(R.id.dish_titleView);
        TextView descriptionView = (TextView) dishView.findViewById(R.id.dish_descriptionView);
        titleView.setText(title);
        descriptionView.setText("Description " + description);
        return dishView;
    }
}
