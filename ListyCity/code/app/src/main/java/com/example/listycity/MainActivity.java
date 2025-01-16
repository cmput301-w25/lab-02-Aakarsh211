package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText input_city;

    Button add_button;
    Button remove_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        input_city = findViewById(R.id.input_city);
        add_button = findViewById(R.id.add_button);
        remove_button = findViewById(R.id.remove_button);

        String[] cities = {"Calgary", "Los Angeles", "Edmonton", "Toronto", "Vancouver", "Raleigh", "Miami", "Washington DC"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE); // Only choosing one city at a time

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, dataList);
        cityList.setAdapter(cityAdapter);

        // Adds a new city (user typed) to the list when the "Add City" button is clicked
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String added_city = input_city.getText().toString().trim();
                // Need to have an input to add a city
                if (!added_city.isEmpty()) {
                    dataList.add(added_city);
                    cityAdapter.notifyDataSetChanged();
                    input_city.setText("");
                }
            }
        });

        // Removes the selected city from the list when the "Delete City" button is clicked
        remove_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = cityList.getCheckedItemPosition();

                // Need to have a city selected to remove
                if (position != ListView.INVALID_POSITION) {
                    dataList.remove(position);
                    cityAdapter.notifyDataSetChanged();
                    cityList.clearChoices(); // Clear selection
                }
            }

        });

    }
}