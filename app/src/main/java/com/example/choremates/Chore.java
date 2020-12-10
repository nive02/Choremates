package com.example.choremates;

public class Chore {
    private String name;
    private String type;
    private String owner;
    private String startDate;
    private String endDate;
    private String frequency;
    private String days;
    private int image;
    private boolean isDone;


    //constructor
    public Chore(){
    }

    public Chore(String n){
        name = n;
    }

    public Chore (String n, int i){
        name = n;
        image = i;
    }

    public Chore (String n, int i, String f, String o, String d, String e, String s, String t){
        name = n;
        type = t;
        owner = o;
        frequency = f;
        days = d;
        endDate = e;
        startDate = s;
        image = i;
    }

    //get methods

    public String getName(){
        return name;
    }

    public String getType(){
        return type;
    }

    public String getOwner(){
        return owner;
    }

    public String getStartDate(){
        return startDate;
    }

    public String getEndDate(){ return endDate; }

    public String getFrequency(){
        return frequency;
    }

    public boolean getIsDone(){ return isDone; }

    public String getDays(){ return days;}

    public int getImage(){return image;}

    //set methods

    public void setName(String n){
        name = n;
    }

    public void setType(String t){
        type = t;
    }

    public void setDays(String d) {days = d;}

    public void setOwner(String o){
        owner = o;
    }

    public void setStartDate(String s){
        startDate = s;
    }

    public void setEndDate(String e){
        endDate = e;
    }

    public void setFrequency(String f){
        frequency = f;
    }

    public void setIsDone(Boolean d){
        isDone = d;
    }

    public void setImage(int i){ image = i; }

}
