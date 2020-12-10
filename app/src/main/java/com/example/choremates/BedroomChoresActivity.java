package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class BedroomChoresActivity extends AppCompatActivity {

    private TextView vacuumCarpet;
    private TextView organizeCloset;
    private TextView makeBed;
    private TextView cleanDrawers;
    private TextView washBedding;
    private TextView organizeDresser;
    private Intent intent;
    public static Chore currentChore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bedroom_chores);

        vacuumCarpet = findViewById(R.id.vacuum_carpet);
        organizeCloset = findViewById(R.id.organize_closet);
        makeBed = findViewById(R.id.make_bed);
        cleanDrawers = findViewById(R.id.clean_drawers_bed);
        washBedding = findViewById(R.id.wash_bedding);
        organizeDresser = findViewById(R.id.organize_dresser);

        intent = new Intent(BedroomChoresActivity.this, FrequencyActivity.class);

        vacuumCarpet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(vacuumCarpet.getText().toString().replace("\n", " "));
                currentChore.setType("Bedroom");
                intent.putExtra("Activity", "Bedroom");
                startActivity(intent);
            }
        });

        organizeCloset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(organizeCloset.getText().toString().replace("\n", " "));
                currentChore.setType("Bedroom");
                intent.putExtra("Activity", "Bedroom");
                startActivity(intent);
            }
        });

        makeBed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(makeBed.getText().toString().replace("\n", " "));
                currentChore.setType("Bedroom");
                intent.putExtra("Activity", "Bedroom");
                startActivity(intent);
            }
        });

        cleanDrawers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanDrawers.getText().toString().replace("\n", " "));
                currentChore.setType("Bedroom");
                intent.putExtra("Activity", "Bedroom");
                startActivity(intent);
            }
        });

        washBedding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(washBedding.getText().toString().replace("\n", " "));
                currentChore.setType("Bedroom");
                intent.putExtra("Activity", "Bedroom");
                startActivity(intent);
            }
        });

        organizeDresser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(organizeDresser.getText().toString().replace("\n", " "));
                currentChore.setType("Bedroom");
                intent.putExtra("Activity", "Bedroom");
                startActivity(intent);
            }
        });

    }

    public static Chore getCurrentChore(){
        return currentChore;
    }
}
