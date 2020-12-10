package com.example.choremates;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Login.db", null, 5);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(email TEXT PRIMARY KEY, password TEXT, roommate1 TEXT, roommate2 TEXT, roommate3 TEXT, roommate4 TEXT)");
        db.execSQL("CREATE TABLE chores(email TEXT, name TEXT, type TEXT, owner TEXT, frequency TEXT, startDate TEXT, endDate TEXT, days TEXT, image INT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS chores");
    }

    /**
     * a method that inserts the information into the database
     * @param email: the email the user signed up with
     * @param password: the password the user entered
     */
    public boolean insert (String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        long ins = db.replace("user", null, contentValues);
        return ins != -1;
    }//end method insert

    /**
     * Check is email exists already
     * @param email: the email that the user is signing up with
     * @return the number of times the email is found in the database
     */
    public boolean checkEmail(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=?", new String[]{email});
        return cursor.getCount() <= 0;
    }//end method checkEmail

    public boolean checkCredentials (String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=? and password=?", new String[]{email, password});
        return cursor.getCount() > 0;
    }//end method checkCredentials

    public boolean changePassword (String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        db.update("user", contentValues, "email=?", new String[]{email});
        return true;
    }//end method checkCredentials

    public boolean insert1 (String r1, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("roommate1", r1);
        long ins = db.update("user", contentValues, "email=?", new String[]{email});
        return ins != -1;
    }

    public boolean insert2 (String r1, String r2, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("roommate1", r1);
        contentValues.put("roommate2", r2);
        long ins = db.update("user", contentValues, "email=?", new String[]{email});
        return ins != -1;
    }

    public boolean insert3 (String r1, String r2, String r3, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("roommate1", r1);
        contentValues.put("roommate2", r2);
        contentValues.put("roommate3", r3);
        long ins = db.update("user", contentValues, "email=?", new String[]{email});
        return ins != -1;
    }

    public boolean insert4 (String r1, String r2, String r3, String r4, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("roommate1", r1);
        contentValues.put("roommate2", r2);
        contentValues.put("roommate3", r3);
        contentValues.put("roommate4", r4);
        long ins = db.update("user", contentValues, "email=?", new String[]{email});
        return ins != -1;
    }

    public Cursor getRoommates(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from user where email=?", new String[]{email});
        return cursor;
    }

    public boolean deleteRoommate(String email, String roomNum){
        String s = null;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(roomNum, s);
        long ins = db.update("user", contentValues, "email=?", new String[]{email});
        return ins != -1;
    }

    public boolean insertRoommate (String r1, String email, String roomNum) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(roomNum, r1);
        long ins = db.update("user", contentValues, "email=?", new String[]{email});
        return ins != -1;
    }

    public boolean update (String key, String email, String update){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(key, update);
        long ins = db.update("user", contentValues, "email=?", new String[]{email});
        return ins != -1;
    }

    public boolean insertChore(String n, String t, String f, String sD, String eD, String o, String email, String days){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", n);
        contentValues.put("type", t);
        contentValues.put("owner", o);
        contentValues.put("frequency", f);
        contentValues.put("startDate", sD);
        contentValues.put("endDate", eD);
        contentValues.put("days", days);
        long ins = db.update("chores", contentValues, "email=?", new String[]{email});
        return ins != -1;
    }

    public boolean insertChore(String name, String type, String frequency, String days, String owner, int image, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("type", type);
        contentValues.put("frequency", frequency);
        contentValues.put("days", days);
        contentValues.put("owner", owner);
        contentValues.put("image", image);
        long ins = db.replace("chores", null, contentValues);
        return ins != -1;
    }

    public Cursor getName(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select name from chores where email=?", new String[]{email});
        return cursor;
    }

    public Cursor getType(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select type from chores where email=?", new String[]{email});
        return cursor;
    }

    public Cursor getFrequency(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select frequency from chores where email=?", new String[]{email});
        return cursor;
    }

    public Cursor getOwner(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select owner from chores where email=?", new String[]{email});
        return cursor;
    }

    public Cursor getDays(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select days from chores where email=?", new String[]{email});
        return cursor;
    }

    public Cursor getEndDate(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select endDate from chores where email=?", new String[]{email});
        return cursor;
    }

    public Cursor getStartDate(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select startDate from chores where email=?", new String[]{email});
        return cursor;
    }

    public Cursor getImage(String email){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select image from chores where email=?", new String[]{email});
        return cursor;
    }

    public boolean deleteChore(String email, String name){
        SQLiteDatabase db = this.getWritableDatabase();
        long ins =  db.delete("chores", "email=? and name=?", new String[]{email, name});
        return ins != -1;
    }

}