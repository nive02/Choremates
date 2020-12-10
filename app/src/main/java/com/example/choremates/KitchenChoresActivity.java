package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class KitchenChoresActivity extends AppCompatActivity {

    private TextView sweepFloors;
    private TextView unloadDishwasher;
    private TextView washDishes;
    private TextView mealPrep;
    private TextView mopFloors;
    private TextView loadDishwasher;
    private TextView cleanMicrowave;
    private TextView cleanTable;
    private TextView groceryShop;
    private TextView cleanFridge;
    private TextView takeOutTrash;
    private TextView cleanStove;
    private Intent intent;
    public static Chore currentChore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_chores);

        sweepFloors = findViewById(R.id.sweep_floors);
        unloadDishwasher = findViewById(R.id.unload_dishwasher);
        washDishes = findViewById(R.id.wash_dishes);
        mealPrep = findViewById(R.id.meal_prep);
        mopFloors = findViewById(R.id.mop_floors);
        loadDishwasher = findViewById(R.id.load_dishwasher);
        cleanMicrowave = findViewById(R.id.clean_microwave);
        cleanTable = findViewById(R.id.clean_table);
        groceryShop = findViewById(R.id.grocery_shop);
        cleanFridge = findViewById(R.id.clean_fridge);
        takeOutTrash = findViewById(R.id.take_out_trash);
        cleanStove = findViewById(R.id.clean_stove);

        intent = new Intent(KitchenChoresActivity.this, FrequencyActivity.class);

        sweepFloors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(sweepFloors.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        unloadDishwasher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(unloadDishwasher.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        washDishes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(washDishes.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        mealPrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(mealPrep.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        mopFloors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(mopFloors.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        loadDishwasher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(loadDishwasher.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        cleanMicrowave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanMicrowave.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        cleanTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanTable.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        groceryShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(groceryShop.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        cleanFridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanFridge.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        takeOutTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(takeOutTrash.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
        cleanStove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanStove.getText().toString().replace("\n", " "));
                currentChore.setType("Kitchen");
                intent.putExtra("Activity", "Kitchen");
                startActivity(intent);
            }
        });
    }

    public static Chore getCurrentChore(){
        return currentChore;
    }

}
