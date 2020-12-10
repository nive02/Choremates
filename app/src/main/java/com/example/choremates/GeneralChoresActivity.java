package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GeneralChoresActivity extends AppCompatActivity {

    private TextView sweep;
    private TextView dust;
    private TextView mop;
    private TextView waterPlants;
    private TextView takeOutGarbage;
    private TextView washCar;
    private Intent intent;
    public static Chore currentChore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_chores);

        sweep = findViewById(R.id.sweep);
        dust = findViewById(R.id.dust);
        mop = findViewById(R.id.mop);
        waterPlants = findViewById(R.id.water_plants);
        takeOutGarbage = findViewById(R.id.take_out_garbage);
        washCar = findViewById(R.id.wash_car);

        intent = new Intent(GeneralChoresActivity.this, FrequencyActivity.class);

        sweep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(sweep.getText().toString());
                currentChore.setType("General");
                intent.putExtra("Activity", "General");
                startActivity(intent);
            }
        });
        dust.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(dust.getText().toString());
                currentChore.setType("General");
                intent.putExtra("Activity", "General");
                startActivity(intent);
            }
        });
        mop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(mop.getText().toString());
                currentChore.setType("General");
                intent.putExtra("Activity", "General");
                startActivity(intent);
            }
        });
        waterPlants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(waterPlants.getText().toString().replace("\n", " "));
                currentChore.setType("General");
                intent.putExtra("Activity", "General");
                startActivity(intent);
            }
        });
        takeOutGarbage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        washCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(washCar.getText().toString().replace("\n", " "));
                currentChore.setType("General");
                intent.putExtra("Activity", "General");
                startActivity(intent);
            }
        });
    }

    public static Chore getCurrentChore(){
        return currentChore;
    }

}
