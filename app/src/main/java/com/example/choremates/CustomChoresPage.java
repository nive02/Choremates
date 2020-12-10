package com.example.choremates;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;


public class CustomChoresPage extends AppCompatActivity {

    EditText choreName;
    Button next;
    String chose;

    Spinner mySpinner;
    String[] chores = {"Kitchen Chores", "Bathroom Chores", "Bedroom Chores", "General Chores", "Living Room Chores"};
    ArrayAdapter<String> myAdapter;
    static Chore currentChore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_chores_page);

        choreName = findViewById(R.id.editText);

        next = findViewById(R.id.addchore);


        mySpinner = findViewById(R.id.spinner);

        myAdapter = new ArrayAdapter<String>(CustomChoresPage.this,
                android.R.layout.simple_list_item_1, chores);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(myAdapter);
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){

                }//end if
                else{
                    chose = parent.getItemAtPosition(position).toString();
                }//end else
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomChoresPage.this, FrequencyActivity.class);
                startActivity(intent);
                currentChore = new Chore();
                currentChore.setName(choreName.getText().toString());
                currentChore.setType(chose);
                intent.putExtra("Activity", "Custom");
                startActivity(intent);
            }
        });
    }

    public static Chore getCurrentChore(){
        return currentChore;
    }

}