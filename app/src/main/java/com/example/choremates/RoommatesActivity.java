package com.example.choremates;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoommatesActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    public Toolbar toolbar;
    ListView roommatesList;

    Button addRoommates;
    TextView notYet;

    TextView edit, done;

    ExpandableListAdapter adapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>(); //list of all the headers
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>(); //HashMap of all the headers and their children

    DatabaseHelper db;
    String email;

    CustomAdapter customAdapter;
    ArrayList<Chore> namesArrayList;

    boolean isEdit = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roommates);


        addRoommates = findViewById(R.id.addRoommateB);
        addRoommates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (namesArrayList.size()<4) {
                    openDialog();
                } else {
                    CustomAlertDialog.openDialog(RoommatesActivity.this, RoommatesActivity.this,
                            (ConstraintLayout)findViewById(R.id.layoutDialogContainer),
                            "Sorry, you can only\nhave 4 roommates", "Attention!");
                }
            }
        });

        done = findViewById(R.id.done_roommates);// click to end the edit mode. Invisible until in edit mode
        done.setVisibility(View.INVISIBLE);


        edit = findViewById(R.id.edit_roommates);//click to edit the list

        //get the email depending on whether the user logged in or signed up
        if (LoginActivity.getEmail() == null || LoginActivity.getEmail().equals("")) {
            email = SignUpActivity.getEmail();
        } else {
            email = LoginActivity.getEmail();
        }


        roommatesList = findViewById(R.id.roommates_list);//the spinner that contains the roommates
        namesArrayList = new ArrayList<Chore>();//the arrayList that is passed to the custom adapter
        db = new DatabaseHelper(this);
        //gets the names of the roommates from the database and adds them to the arrayList
        final Cursor cursor = db.getRoommates(email);
        while (cursor.moveToNext()) {
            for (int i = 2; i < 6; i++) {
                if (cursor.getString(i) != null) {
                    namesArrayList.add(new Chore(cursor.getString(i)));
                }
            }
        }

        customAdapter = new CustomAdapter(namesArrayList, RoommatesActivity.this, email, notYet, "RoommatesActivity");
        roommatesList.setAdapter(customAdapter);

        //edit mode
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit = true;
                edit.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);
                CustomAdapter c = new CustomAdapter(namesArrayList, RoommatesActivity.this, email, notYet, "RoommatesActivity");
                c.edit(true);
                roommatesList.setAdapter(c);
                c.notifyDataSetChanged();

                c = new CustomAdapter(namesArrayList, RoommatesActivity.this, email, notYet, "RoommatesActivity");
                c.edit(true);
                roommatesList.setAdapter(c);

            }
        });

        //exit edit mode
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit = false;
                edit.setVisibility(View.VISIBLE);
                done.setVisibility(View.INVISIBLE);
                CustomAdapter c = new CustomAdapter(namesArrayList, RoommatesActivity.this, email, notYet, "RoommatesActivity");
                c.edit(false);
                roommatesList.setAdapter(customAdapter);
            }
        });

        //code for the navigation
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expandableListView = findViewById(R.id.expandableListView);

        prepareMenuData();
        populateExpandableList();

        drawer = findViewById(R.id.chores_drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.red));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }//end method onCreate

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }//end onBackPressed

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void prepareMenuData() {

        MenuModel profileM = new MenuModel("Roommates", true, false);
        headerList.add(profileM);

        if (!profileM.hasChildren) {
            childList.put(profileM, null);
        }

        MenuModel myChoresM = new MenuModel("My Chores", true, false);
        headerList.add(myChoresM);

        if (!myChoresM.hasChildren) {
            childList.put(myChoresM, null);
        }

        MenuModel addChoresM = new MenuModel("Add Chores", true, true);
        headerList.add(addChoresM);
        List<MenuModel> childModelsList = new ArrayList<>();
        MenuModel kitchen = new MenuModel("Kitchen Chores", false, false);
        childModelsList.add(kitchen);

        MenuModel bathroom = new MenuModel("Bathroom Chores", false, false);
        childModelsList.add(bathroom);

        MenuModel livingRoom = new MenuModel("Living room Chores", false, false);
        childModelsList.add(livingRoom);

        MenuModel general = new MenuModel("General Chores", false, false);
        childModelsList.add(general);

        MenuModel bedroom = new MenuModel("Bedroom Chores", false, false);
        childModelsList.add(bedroom);

        MenuModel custom = new MenuModel("Custom Chores", false, false);
        childModelsList.add(custom);

        if (addChoresM.hasChildren) {
            childList.put(addChoresM, childModelsList);
        }

        MenuModel calendarM = new MenuModel("Calendar", true, false); //Menu of Python Tutorials
        headerList.add(calendarM);

        if (!calendarM.hasChildren) {
            childList.put(calendarM, null);
        }
    }

    private void populateExpandableList() {

        adapter = new ExpandableListAdapter(this, headerList, childList);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                String gp = Integer.toString(groupPosition);
                if (headerList.get(groupPosition).isGroup) {
                    if (!headerList.get(groupPosition).hasChildren) {
                        if (gp.equals("0")) {
                            startActivity(new Intent(getApplicationContext(), RoommatesActivity.class));
                        } else if (gp.equals("1")) {
                            startActivity(new Intent(getApplicationContext(), ChoresActivity.class));
                        } else if (gp.equals("3")) {
                            //go to calendar page
                        }
                    }
                }

                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String cp = Integer.toString(childPosition);
                if (childList.get(headerList.get(groupPosition)) != null) {
                    switch (cp) {
                        case "0":
                            startActivity(new Intent(getApplicationContext(), KitchenChoresActivity.class));
                            break;
                        case "1":
                            startActivity(new Intent(getApplicationContext(), BathroomChoresActivity.class));
                            break;
                        case "2":
                            startActivity(new Intent(getApplicationContext(), LivingRoomChoresActivity.class));
                            break;
                        case "3":
                            startActivity(new Intent(getApplicationContext(), GeneralChoresActivity.class));
                            break;
                        case "4":
                            startActivity(new Intent(getApplicationContext(), BedroomChoresActivity.class));
                            break;
                        case "5":
                    }
                }

                return false;
            }
        });
    }//end method

    private void openDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        final View view = getLayoutInflater().inflate(R.layout.layout_alert_dialog_roommate, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        final DatabaseHelper db2 = new DatabaseHelper(this);
        final Cursor cursor = db2.getRoommates(email);
        view.findViewById(R.id.pos_button_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for 4 rows
                for (int i = 0; i < 5; i++) {
                    //gets the names of the roommates from the database and adds them to the arrayList
                    while (cursor.moveToNext()) {
                        for (int k = 2; k < 6; k++) {
                            if (cursor.getString(k) == null) {
                                db.insertRoommate(((EditText)view.findViewById(R.id.roommate_name_edit)).getText().toString(), email, "roommate"+(k-1));
                                namesArrayList.add(new Chore(((EditText)view.findViewById(R.id.roommate_name_edit)).getText().toString()));
                                CustomAdapter c = new CustomAdapter(namesArrayList, RoommatesActivity.this, email, notYet, "RoommatesActivity");
                                roommatesList.setAdapter(c);
                                c.notifyDataSetChanged();
                                alertDialog.dismiss();
                                break;
                            }
                        }
                    }
                }
            }
        });

        view.findViewById(R.id.neg_button_room).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null){
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();
    }//end method open dialog

}//end class
