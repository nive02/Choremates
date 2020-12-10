package com.example.choremates;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddRoommatesActivity extends AppCompatActivity {

    public static int numRoommates;
    public static String[] roommates;
    private Button oneB, twoB, threeB, fourB, next;
    private EditText oneE, twoE, threeE, fourE;
    private TextView blurb;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_roommates);

        oneB = findViewById(R.id.one_roommates_button);
        twoB = findViewById(R.id.two_roommate_button);
        threeB = findViewById(R.id.three_roommate_button);
        fourB = findViewById(R.id.four_roommates_button);
        next = findViewById(R.id.next);
        blurb = findViewById(R.id.enter_name_blurb);
        db = new DatabaseHelper(AddRoommatesActivity.this);

        oneE = findViewById(R.id.one_roommate_editText);
        twoE = findViewById(R.id.two_roommate_editText);
        threeE = findViewById(R.id.three_roommate_editText);
        fourE = findViewById(R.id.four_roommate_editText);

        oneE.setVisibility(View.INVISIBLE);
        twoE.setVisibility(View.INVISIBLE);
        threeE.setVisibility(View.INVISIBLE);
        fourE.setVisibility(View.INVISIBLE);
        blurb.setVisibility(View.INVISIBLE);
        next.setVisibility(View.INVISIBLE);

        final ConstraintLayout constraintLayout = findViewById(R.id.add_roommates_layout);
        final ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        oneB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurb.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                numRoommates = 1;
                oneE.setVisibility(View.VISIBLE);
                twoE.setVisibility(View.INVISIBLE);
                threeE.setVisibility(View.INVISIBLE);
                fourE.setVisibility(View.INVISIBLE);
                roommates = new String[numRoommates];
            }
        });

        twoB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurb.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                numRoommates = 2;
                oneE.setVisibility(View.VISIBLE);
                twoE.setVisibility(View.VISIBLE);
                threeE.setVisibility(View.INVISIBLE);
                fourE.setVisibility(View.INVISIBLE);
                roommates = new String[numRoommates];
            }
        });

        threeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurb.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                numRoommates = 3;
                oneE.setVisibility(View.VISIBLE);
                twoE.setVisibility(View.VISIBLE);
                threeE.setVisibility(View.VISIBLE);
                fourE.setVisibility(View.INVISIBLE);
                roommates = new String[numRoommates];
            }
        });

        fourB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                blurb.setVisibility(View.VISIBLE);
                next.setVisibility(View.VISIBLE);
                numRoommates = 4;
                oneE.setVisibility(View.VISIBLE);
                twoE.setVisibility(View.VISIBLE);
                threeE.setVisibility(View.VISIBLE);
                fourE.setVisibility(View.VISIBLE);
                roommates = new String[numRoommates];
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (numRoommates){
                    case 1:
                        if (oneE.getText().toString().equals("")){
                            CustomAlertDialog.openDialogNoTitle(AddRoommatesActivity.this, AddRoommatesActivity.this, (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                                    "Please enter a name.");
                             }
                        roommates[0] = oneE.getText().toString();
                        db.insert1(roommates[0], SignUpActivity.getEmail());
                        break;
                    case 2:
                        if (oneE.getText().toString().equals("")|| twoE.getText().toString().equals("")){
                            CustomAlertDialog.openDialogNoTitle(AddRoommatesActivity.this, AddRoommatesActivity.this, (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                                    "Please enter a name.");
                        }
                        roommates[0] = oneE.getText().toString();
                        roommates[1] = twoE.getText().toString();
                        db.insert2(roommates[0], roommates[1], SignUpActivity.getEmail());
                        break;     
                    case 3:
                        if (oneE.getText().toString().equals("")|| twoE.getText().toString().equals("") || threeE.getText().toString().equals("")){
                            CustomAlertDialog.openDialogNoTitle(AddRoommatesActivity.this, AddRoommatesActivity.this, (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                                    "Please enter a name.");
                        }
                        roommates[0] = oneE.getText().toString();
                        roommates[1] = twoE.getText().toString();
                        roommates[2] = threeE.getText().toString();
                        db.insert3(roommates[0], roommates[1], roommates[2], SignUpActivity.getEmail());
                        break;
                    case 4:
                        if (oneE.getText().toString().equals("")|| twoE.getText().toString().equals("") || threeE.getText().toString().equals("") || fourE.getText().toString().equals("")){
                            CustomAlertDialog.openDialogNoTitle(AddRoommatesActivity.this, AddRoommatesActivity.this, (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                                    "Please enter a name.");
                        }
                        roommates[0] = oneE.getText().toString();
                        roommates[1] = twoE.getText().toString();
                        roommates[2] = threeE.getText().toString();
                        roommates[3] = fourE.getText().toString();
                        db.insert4(roommates[0], roommates[1], roommates[2], roommates[3], SignUpActivity.getEmail());
                        break;
                }
                Intent intent = new Intent(AddRoommatesActivity.this, ChoresActivity.class);
                startActivity(intent);

            }
        });
    }

    public static String[] getRoommates(){ return roommates; }
}
