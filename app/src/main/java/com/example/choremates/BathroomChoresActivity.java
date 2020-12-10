package com.example.choremates;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BathroomChoresActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private TextView sweepFloors;
    private TextView cleanToilet;
    private TextView buyTP;
    private TextView cleanDrawers;
    private TextView cleanMirror;
    private TextView cleanSink;
    private TextView cleanBathtub;
    private TextView washRug;
    private Intent intent;
    public static Chore currentChore;

    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    public Toolbar toolbar;

    ExpandableListAdapter adapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList  = new ArrayList<>(); //list of all the headers
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>(); //hashmap of all the headers and their children

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bathroom_chores);

        sweepFloors = findViewById(R.id.sweep_floors_bath);
        cleanToilet = findViewById(R.id.clean_toilet);
        buyTP = findViewById(R.id.buy_toilet_paper);
        cleanDrawers = findViewById(R.id.clean_drawers);
        cleanMirror = findViewById(R.id.clean_mirror);
        cleanSink = findViewById(R.id.clean_sink);
        cleanBathtub = findViewById(R.id.clean_bathtub);
        washRug = findViewById(R.id.wash_rugs);

        intent = new Intent(BathroomChoresActivity.this, FrequencyActivity.class);

        sweepFloors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(sweepFloors.getText().toString().replace("\n", " "));
                currentChore.setType("Bathroom");
                intent.putExtra("Activity", "Bathroom");
                startActivity(intent);
            }
        });
        cleanToilet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanToilet.getText().toString().replace("\n", " "));
                currentChore.setType("Bathroom");
                intent.putExtra("Activity", "Bathroom");
                startActivity(intent);
            }
        });
        buyTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(buyTP.getText().toString().replace("\n", " "));
                currentChore.setType("Bathroom");
                intent.putExtra("Activity", "Bathroom");
                startActivity(intent);
            }
        });
        cleanDrawers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanDrawers.getText().toString().replace("\n", " "));
                currentChore.setType("Bathroom");
                intent.putExtra("Activity", "Bathroom");
                startActivity(intent);
            }
        });
        cleanMirror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanMirror.getText().toString().replace("\n", " "));
                currentChore.setType("Bathroom");
                intent.putExtra("Activity", "Bathroom");
                startActivity(intent);
            }
        });
        cleanSink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanSink.getText().toString().replace("\n", " "));
                currentChore.setType("Bathroom");
                intent.putExtra("Activity", "Bathroom");
                startActivity(intent);
            }
        });
        cleanBathtub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(cleanBathtub.getText().toString().replace("\n", " "));
                currentChore.setType("Bathroom");
                intent.putExtra("Activity", "Bathroom");
                startActivity(intent);
            }
        });
        washRug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentChore = new Chore();
                currentChore.setName(washRug.getText().toString().replace("\n", " "));
                currentChore.setType("Bathroom");
                intent.putExtra("Activity", "Bathroom");
                startActivity(intent);
            }
        });

        toolbar = findViewById(R.id.toolbarB);
        setSupportActionBar(toolbar);

        expandableListView = findViewById(R.id.expandableListView);

        prepareMenuData();
        populateExpandableList();

        drawer = findViewById(R.id.bathroom_drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.red));
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_viewB);
        navigationView.setNavigationItemSelectedListener(this);

    }

    public static Chore getCurrentChore(){
        return currentChore;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)){
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

        MenuModel profileM = new MenuModel("Roommates", true, false); //Menu of Android Tutorial. No sub menus
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

        MenuModel calendarM = new MenuModel("Calendar", true, false);
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
                        if (gp.equals("0")){
                            startActivity(new Intent (getApplicationContext(), RoommatesActivity.class));
                        } else if (gp.equals("1")){
                            startActivity(new Intent (getApplicationContext(), ChoresActivity.class));
                        } else if (gp.equals("3")){
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
                    switch (cp){
                        case "0":
                            startActivity(new Intent (getApplicationContext(), KitchenChoresActivity.class));
                            break;
                        case "1":
                            startActivity(new Intent (getApplicationContext(), BathroomChoresActivity.class));
                            break;
                        case "2":
                            startActivity(new Intent (getApplicationContext(), LivingRoomChoresActivity.class));
                            break;
                        case "3":
                            startActivity(new Intent (getApplicationContext(), GeneralChoresActivity.class));
                            break;
                        case "4":
                            startActivity(new Intent (getApplicationContext(), BedroomChoresActivity.class));
                            break;
                        case "5":
                            startActivity(new Intent (getApplicationContext(), CustomChoresPage.class));
                            break;
                    }
                }

                return false;
            }
        });
    }//end method

}
