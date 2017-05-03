package com.example.tomas.mandatorycanteenapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tomas on 07-04-2017.
 */

public class MenuActivity extends AppCompatActivity implements GestureDetector.OnGestureListener
{

    GestureDetector gesture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    @Override
    protected void onStart() {
        super.onStart();
        ReadTask task = new ReadTask();
        task.execute("http://anbo-canteen.azurewebsites.net/Service1.svc/dishes");
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        //Toast.makeText(this, "onScroll", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);

            //messageTextView.setText(charSequence);
            final List<Dish> dishes = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(charSequence.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);

                    double alcohol = obj.getDouble("Alcohol");
                    double carbohydrates = obj.getDouble("Carbohydrates");
                    String description = obj.getString("Description");
                    int energy = obj.getInt("Energy");
                    int fat = obj.getInt("Fat");
                    int id = obj.getInt("Id");
                    double price = obj.getDouble("Price");
                    double protein = obj.getDouble("Protein");
                    String title = obj.getString("Title");
                    double weight = obj.getDouble("Weight");

                    Dish dish = new Dish(alcohol, carbohydrates, description, energy, fat, id, price, protein, title, weight);
                    dishes.add(dish);


                }
              ListView listView = (ListView) findViewById(R.id.dishListView);
                //ArrayAdapter<Book> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, books);
                DishListAdapter adapter = new DishListAdapter(getBaseContext(), R.layout.dish_list_item, dishes);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getBaseContext(), DishActivity.class);
                        intent.putExtra("DISH", dishes.get((int) id));
                        startActivity(intent);
                    }
                });
            } catch (JSONException ex) {

                Log.e("DISH", ex.getMessage());
            }
        }

//        @Override
//        protected void onCancelled(CharSequence charSequence) {
//            super.onCancelled();
//            TextView messageTextView = (TextView) findViewById(R.id.main_message_textview);
//            messageTextView.setText(charSequence);
//            Log.e("BOOKS", charSequence.toString());
//        }
    }

}
