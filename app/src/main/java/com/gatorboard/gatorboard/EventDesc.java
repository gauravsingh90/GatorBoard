package com.gatorboard.gatorboard;

import android.graphics.Bitmap;

/**
 * Created by Aswini on 11/3/2015.
 */
public class EventDesc {



        private int EventId;
        private String EventName;

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

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

        private String evntStartDate;
        private String evntEndDate;
        private String evntStartTime;
        private String evntEndTime;
        private String evntLocation;
        private String evntDesc;
        private String evntTags;
        private String evntImgURL;
        private Bitmap bitmap;

    public String getEvntStartDate() {
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




    public Bitmap getBitmap() {
            return bitmap;
        }

   /* public EventDesc(int EId,String EName,String StartDate,String EndDate, String StartTime, String EndTime, String eLocation, String eDesc, String eTags, String eImgURL, Bitmap bitmap)
    {
        EventId = EId;
        EventName = EName;
        evntStartDate=StartDate;
        evntEndDate = EndDate;
        evntStartTime = StartTime;
        evntEndTime = EndDate;
        evntLocation = eLocation;
        evntDesc = eDesc;
        evntTags = eTags;
         evntImgURL = eImgURL;
         bitmap = bitmap;
    }*/




    @Override
        public String toString(){
            return EventName;
        }
    }


