package com.example.choremates;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChoresActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    public Toolbar toolbar;
    ListView choresList;

    Button addChores;
    TextView notYet;

    TextView edit, done;

    ExpandableListAdapter adapter;
    ExpandableListView expandableListView;
    List<MenuModel> headerList = new ArrayList<>(); //list of all the headers
    HashMap<MenuModel, List<MenuModel>> childList = new HashMap<>(); //HashMap of all the headers and their children

    ArrayList<String> chores, type, owner, frequency, days, startDate, endDate;
    ArrayList<Integer> image;

    DatabaseHelper db;
    String email;

    CustomAdapter customAdapter;
    ArrayList<Chore> choresArrayList;

    boolean isEdit = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chores);

        addChores = findViewById(R.id.addChoresB);
        addChores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoresActivity.this, AddChoresActivity.class);
                startActivity(intent);

            }
        });

        done = findViewById(R.id.done_chorespage);
        done.setVisibility(View.INVISIBLE);


        edit = findViewById(R.id.edit_chorespage);

        if (LoginActivity.getEmail() == null || LoginActivity.getEmail().equals("")) {
            email = SignUpActivity.getEmail();
        } else {
            email = LoginActivity.getEmail();
        }



        choresList = findViewById(R.id.chores_list);
        chores = new ArrayList<>();
        image = new ArrayList<>();
        type = new ArrayList<>();
        owner = new ArrayList<>();
        frequency = new ArrayList<>();
        days = new ArrayList<>();
        endDate = new ArrayList<>();
        startDate = new ArrayList<>();
        db = new DatabaseHelper(this);

        final Cursor cursor = db.getName(email);
        while (cursor.moveToNext()) {
            int i = cursor.getColumnIndexOrThrow("name");
            chores.add(cursor.getString(i));
        }

        final Cursor cursor1 = db.getImage(email);
        while (cursor1.moveToNext()) {
            int i = cursor1.getColumnIndexOrThrow("image");
            image.add(cursor1.getInt(i));
        }

        final Cursor cursor2 = db.getType(email);
        while (cursor2.moveToNext()) {
            int i = cursor2.getColumnIndexOrThrow("type");
            type.add(cursor2.getString(i));
        }

        final Cursor cursor3 = db.getFrequency(email);
        while (cursor3.moveToNext()) {
            int i = cursor3.getColumnIndexOrThrow("frequency");
            frequency.add(cursor3.getString(i));
        }

        final Cursor cursor4 = db.getOwner(email);
        while (cursor4.moveToNext()) {
            int i = cursor4.getColumnIndexOrThrow("owner");
            owner.add(cursor4.getString(i));
        }

        final Cursor cursor5 = db.getDays(email);
        while (cursor5.moveToNext()) {
            int i = cursor5.getColumnIndexOrThrow("days");
            days.add(cursor5.getString(i));
        }

        final Cursor cursor6 = db.getEndDate(email);
        while (cursor6.moveToNext()) {
            int i = cursor6.getColumnIndexOrThrow("endDate");
            endDate.add(cursor6.getString(i));
        }

        final Cursor cursor7 = db.getStartDate(email);
        while (cursor7.moveToNext()) {
            int i = cursor7.getColumnIndexOrThrow("startDate");
            startDate.add(cursor7.getString(i));
        }

        choresArrayList = new ArrayList<>();
        for (int i = 0; i < chores.size(); i++){
            choresArrayList.add(new Chore(chores.get(i), image.get(i), frequency.get(i), owner.get(i), days.get(i),
                                          endDate.get(i), startDate.get(i), type.get(i)));
        }

        notYet = findViewById(R.id.chores_blurb);
        if (!chores.isEmpty()){
            notYet.setVisibility(View.INVISIBLE);
        }

        customAdapter = new CustomAdapter(choresArrayList, ChoresActivity.this, email, notYet, "ChoresActivity");
        choresList.setAdapter(customAdapter);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit = true;
                edit.setVisibility(View.INVISIBLE);
                done.setVisibility(View.VISIBLE);
                CustomAdapter c = new CustomAdapter(choresArrayList, ChoresActivity.this, email, notYet, "ChoresActivity");
                c.edit(true);
                choresList.setAdapter(c);
                c.notifyDataSetChanged();

                c = new CustomAdapter(choresArrayList, ChoresActivity.this, email, notYet, "ChoresActivity");
                c.edit(true);
                choresList.setAdapter(c);

            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEdit = false;
                edit.setVisibility(View.VISIBLE);
                done.setVisibility(View.INVISIBLE);
                CustomAdapter c = new CustomAdapter(choresArrayList, ChoresActivity.this, email, notYet, "ChoresActivity");
                c.edit(false);
                choresList.setAdapter(customAdapter);
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

}//end class
