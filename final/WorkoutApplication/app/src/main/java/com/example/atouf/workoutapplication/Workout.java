package com.example.atouf.workoutapplication;

/**
 * Created by atouf on 11/15/2017.
 */

public class Workout {
    private String wType;
    private String wResult;
    private int wImageResourceID = NO_IMAGE_PROVIDED;
    private String youtubeLink;
    private static final int NO_IMAGE_PROVIDED = -1;
    private String reps;
    private String sets;

    public Workout(String type, String result){
        wType = type;
        wResult = result;
    }

    public Workout(String type, String result, int ImageResourceID){
        wType = type;
        wResult = result;
        wImageResourceID = ImageResourceID;
    }


    public Workout(String type, String result, int ImageResourceID, String videoLink, String rep, String set){
        wType = type;
        wResult = result;
        wImageResourceID = ImageResourceID;
        youtubeLink = videoLink;
        reps = rep;
        sets = set;
    }

    public String getType() {return wType;}
    public String getResult() {return wResult;}
    public String getYoutube() {return youtubeLink;}
    public String getReps() {return reps;}
    public String getSets() {return sets;}
    public int getImageResourceId() {return wImageResourceID;}
    public boolean hasImage() {return wImageResourceID != NO_IMAGE_PROVIDED;}
    public boolean hasLink() {return youtubeLink != null;}
}
