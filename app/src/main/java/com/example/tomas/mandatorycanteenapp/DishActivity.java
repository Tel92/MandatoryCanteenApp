package com.example.tomas.mandatorycanteenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DishActivity extends AppCompatActivity {
    private Dish dish;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish);

        Intent intent = getIntent();
        dish = (Dish) intent.getSerializableExtra("DISH");

        TextView idView = (TextView) findViewById(R.id.dish_idView);
        idView.setText("Dish Id=" + dish.getId());

        TextView titleView = (TextView) findViewById(R.id.dish_titleView);
        titleView.setText("Dish Title=" + dish.getTitle());

        TextView descriptionView = (TextView) findViewById(R.id.dish_descriptionView);
        descriptionView.setText("Dish Description=" + dish.getDescription());

        TextView priceView = (TextView) findViewById(R.id.dish_priceView);
        priceView.setText("Dish Price=" + dish.getPrice());

        TextView weightView = (TextView) findViewById(R.id.dish_weightView);
        weightView.setText("Dish Weight=" + dish.getWeight());

        TextView energyView = (TextView) findViewById(R.id.dish_energyView);
        energyView.setText("Dish Energy=" + dish.getEnergy());

        TextView carbohydratesView = (TextView) findViewById(R.id.dish_carbohydratesView);
        carbohydratesView.setText("Dish Carbohydrates=" + dish.getCarbohydrates());

        TextView fatView = (TextView) findViewById(R.id.dish_fatView);
        fatView.setText("Dish Fat=" + dish.getFat());

        TextView protienView = (TextView) findViewById(R.id.dish_alcoholView);
        protienView.setText("Dish Protein=" + dish.getProtein());

        TextView alcoholView = (TextView) findViewById(R.id.dish_proteinView);
        alcoholView.setText("Dish Alcohol=" + dish.getAlcohol());

    }

}
