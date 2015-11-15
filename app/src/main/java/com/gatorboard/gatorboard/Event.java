package com.gatorboard.gatorboard;
import android.graphics.Bitmap;

/**
 * Created by sushma on 10/6/2015.
 */
public class Event {


    private String eventID;
    private String evntName;
    private String evntStartDate;
    private String evntStartTime;
    private String evntLocation;
    private String evntDesc;
    private String likes;
    private String catname;
    private String evntImgURL;
    private Bitmap bitmap;

    public Event(String eventID, String evntName, String evntStartDate, String evntStartTime, String evntLocation, String evntDesc,String likes, String catname, String evntImgURL, Bitmap bitmap)
                 {
       this.eventID = eventID;
                     this.evntName=evntName;
                     this.evntStartDate=evntStartDate;
                     this.evntStartTime=evntStartTime;
                     this.evntLocation=evntLocation;
                     this.evntDesc=evntDesc;
                     this.likes=likes;
                     this.catname=catname;
                     this.evntImgURL=evntImgURL;
                     this.bitmap=bitmap;
    }

    public Event(){

    }


    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEvntName() {
        return evntName;
    }

    public void setEvntName(String evntName) {
        this.evntName = evntName;
    }

    public String getEvntStartDate() {
        return evntStartDate;
    }

    public void setEvntStartDate(String evntStartDate) {
        this.evntStartDate = evntStartDate;
    }

    public String getEvntStartTime() {
        return evntStartTime;
    }

    public void setEvntStartTime(String evntStartTime) {
        this.evntStartTime = evntStartTime;
    }

    public String getEvntLocation() {
        return evntLocation;
    }

    public void setEvntLocation(String evntLocation) {
        this.evntLocation = evntLocation;
    }

    public String getEvntDesc() {
        return evntDesc;
    }

    public void setEvntDesc(String evntDesc) {
        this.evntDesc = evntDesc;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getEvntImgURL() {
        return evntImgURL;
    }

    public void setEvntImgURL(String evntImgURL) {
        this.evntImgURL = evntImgURL;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
