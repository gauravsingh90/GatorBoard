package com.gatorboard.gatorboard;
import android.graphics.Bitmap;

/**
 * Created by sushma on 10/6/2015.
 */
public class Event {


    private int EventId;
    private String EventName;
    private String EventDesc;
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

    public Event(int EId,String EName, String EDesc)
    {
        EventId = EId;
        EventName = EName;
        EventDesc = EDesc;
    }


    public int getEventId()
    {
        return EventId;
    }

    public String getEventName()
    {

        return EventName;
    }

    public String getEventDesc()
    {

        return EventDesc;
    }
    public void setEventId(int eventId) {
        EventId = eventId;
    }
    public void setEventName(String eventName) {
        EventName = eventName;
    }
    public void setEventDesc(String eventDesc) {
        EventDesc = eventDesc;
    }

    @Override
    public String toString(){
        return EventName;
    }
}
