package com.example.choremates;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.BaseTransientBottomBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddChoresActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Button kitchen;
    private Button bedroom;
    private Button bathroom;
    private Button general;
    private Button custom;
    private Button livingRoom;

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
        setContentView(R.layout.activity_add_chores);

        kitchen = findViewById(R.id.kitchenButton);
        kitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, KitchenChoresActivity.class);
                startActivity(intent);
            }
        });

        bedroom = findViewById(R.id.bedroomButton);
        bedroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, BedroomChoresActivity.class);
                startActivity(intent);
            }
        });

        bathroom = findViewById(R.id.bathroomButton);
        bathroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, BathroomChoresActivity.class);                startActivity(intent);
                startActivity(intent);
            }
        });

        general = findViewById(R.id.generalButton);
        general.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, GeneralChoresActivity.class);
                startActivity(intent);
            }
        });

        livingRoom = findViewById(R.id.livingButton);
        livingRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, LivingRoomChoresActivity.class);
                startActivity(intent);
            }
        });

        custom = findViewById(R.id.customButton);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddChoresActivity.this, CustomChoresPage.class);
                startActivity(intent);
            }
        });

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
