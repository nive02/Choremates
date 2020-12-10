package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LivingRoomChoresActivity extends AppCompatActivity {

    private TextView polishFurniture;
    private TextView dustCobwebs;
    private TextView plumpPillows;
    private TextView wipeTV;
    private TextView pickUpPetToys;
    private TextView moveDishesSink;
    private TextView washWindows;
    private TextView wipeTable;
    public static Chore currentChore;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_living_room_chores);

        polishFurniture = findViewById(R.id.polish_furniture);
        dustCobwebs = findViewById(R.id.dust_cobwebs);
        plumpPillows = findViewById(R.id.plump_pillows);
        wipeTV = findViewById(R.id.wipe_tv);
        pickUpPetToys = findViewById(R.id.pick_up_pet_toys);
        moveDishesSink = findViewById(R.id.move_dishes_sink);
        washWindows = findViewById(R.id.wash_windows);
        wipeTable = findViewById(R.id.wipe_table);

        intent = new Intent(LivingRoomChoresActivity.this, FrequencyActivity.class);

        polishFurniture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(polishFurniture.getText().toString().replace("\n", " "));
                currentChore.setType("Living room");
                intent.putExtra("Activity", "LivingRoom");
                startActivity(intent);
            }
        });
        dustCobwebs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(dustCobwebs.getText().toString().replace("\n", " "));
                currentChore.setType("Living room");
                intent.putExtra("Activity", "LivingRoom");
                startActivity(intent);
            }
        });
        plumpPillows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(plumpPillows.getText().toString().replace("\n", " "));
                currentChore.setType("Living room");
                intent.putExtra("Activity", "LivingRoom");
                startActivity(intent);
            }
        });
        wipeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(wipeTV.getText().toString().replace("\n", " "));
                currentChore.setType("Living room");
                intent.putExtra("Activity", "LivingRoom");
                startActivity(intent);
            }
        });
        pickUpPetToys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(pickUpPetToys.getText().toString().replace("\n", " "));
                currentChore.setType("Living room");
                intent.putExtra("Activity", "LivingRoom");
                startActivity(intent);
            }
        });
        moveDishesSink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(moveDishesSink.getText().toString().replace("\n", " "));
                currentChore.setType("Living room");
                intent.putExtra("Activity", "LivingRoom");
                startActivity(intent);
            }
        });
        washWindows.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(washWindows.getText().toString().replace("\n", " "));
                currentChore.setType("Living room");
                intent.putExtra("Activity", "LivingRoom");
                startActivity(intent);
            }
        });
        wipeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(wipeTable.getText().toString().replace("\n", " "));
                currentChore.setType("Living room");
                intent.putExtra("Activity", "LivingRoom");
                startActivity(intent);
            }
        });
    }

    public static Chore getCurrentChore(){
        return currentChore;
    }

}
