package com.gatorboard.gatorboard;
import android.graphics.Bitmap;

/**
 * Created by sushma on 10/6/2015.
 */
public class Event {


    private int EventId;
    private String EventName;
    private String CategoryID;
    //picture to be displayed in scrollview of images
    private String photo;

    public Event(int eventId, String eventName, String categoryID, Bitmap bitmap) {
        EventId = eventId;
        EventName = eventName;
        CategoryID = categoryID;
        this.bitmap = bitmap;
    }

    private Bitmap bitmap;

    //additional fields if it is possible to encapsulate this in one webservice
    /*
    private String evntStartDate;
    private String evntEndDate;
    private String evntStartTime;
    private String evntEndTime;
    private String evntLocation;
    private String evntDesc;
    private String evntTags;

    //picture to be displayed in eventDescription
    private String evntImgURL;
   */

    public int getEventId() {
        return EventId;
    }

    public void setEventId(int eventId) {
        EventId = eventId;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(String categoryID) {
        CategoryID = categoryID;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   /* public String getEvntStartDate() {
        return evntStartDate;
    }

    public void setEvntStartDate(String evntStartDate) {
        this.evntStartDate = evntStartDate;
    }

    public String getEvntEndDate() {
        return evntEndDate;
    }

    public void setEvntEndDate(String evntEndDate) {
        this.evntEndDate = evntEndDate;
    }

    public String getEvntStartTime() {
        return evntStartTime;
    }

    public void setEvntStartTime(String evntStartTime) {
        this.evntStartTime = evntStartTime;
    }

    public String getEvntEndTime() {
        return evntEndTime;
    }

    public void setEvntEndTime(String evntEndTime) {
        this.evntEndTime = evntEndTime;
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

    public String getEvntTags() {
        return evntTags;
    }

    public void setEvntTags(String evntTags) {
        this.evntTags = evntTags;
    }

    public String getEvntImgURL() {
        return evntImgURL;
    }

    public void setEvntImgURL(String evntImgURL) {
        this.evntImgURL = evntImgURL;
    }
*/
    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public String toString(){
        return EventName;
    }
}
