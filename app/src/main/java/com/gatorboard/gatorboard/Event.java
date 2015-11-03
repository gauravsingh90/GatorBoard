package com.gatorboard.gatorboard;
import android.graphics.Bitmap;

/**
 * Created by sushma on 10/6/2015.
 */
public class Event {


    private int EventId;
    private String EventName;
    private String CategoryID;
    private String photo;
    private Bitmap bitmap;

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getPhoto() {
        return photo;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Event(int EId,String EName, String catID, Bitmap bit)
    {
        EventId = EId;
        EventName = EName;
        CategoryID= catID;
        bitmap = bit;
    }


    public int getEventId()
    {
        return EventId;
    }

    public String getEventName()
    {

        return EventName;
    }


    public void setEventId(int eventId) {
        EventId = eventId;
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

    @Override
    public String toString(){
        return EventName;
    }
}
