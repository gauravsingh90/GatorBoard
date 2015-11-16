package com.gatorboard.gatorboard;

/**
 * Created by sushma on 11/12/2015.
 */
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SitesXmlPullParser {

    static final String KEY_EVENT = "event";
    static final String KEY_ID = "eventID";
    static final String KEY_NAME = "evntName";
    static final String KEY_DATE = "evntStartDate";
    static final String KEY_TIME = "evntStartTime";
    static final String KEY_LOCATION = "evntLocation";
    static final String KEY_DESC = "evntDesc";
    static final String KEY_CATNAME = "catname";
    static final String KEY_IMAGE_URL = "evntImgURL";
    static final String KEY_lIKES = "likes";


    public static List<Event> getEventsFromFile(Context ctx) {

        // List of Events that we will return
        List<Event> Events;
        Events = new ArrayList<Event>();

        // temp holder for current Event while parsing
        Event curEvent = null;
        // temp holder for current text value while parsing
        String curText = "";

        try {
            // Get our factory and PullParser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();

            // Open up InputStream and Reader of our file.
            FileInputStream fis =  ctx.openFileInput("event.xml");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            // point the parser to our file.
            xpp.setInput(reader);

            // get initial eventType
            int eventType = xpp.getEventType();

            // Loop through pull events until we reach END_DOCUMENT
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // Get the current tag
                String tagname = xpp.getName();

                // React to different event types appropriately
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase(KEY_EVENT)) {
                            // If we are starting a new <site> block we need
                            //a new StackSite object to represent it
                            curEvent = new Event();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        //grab the current text so we can use it in END_TAG event
                        curText = xpp.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase(KEY_EVENT)) {
                            // if </site> then we are done with current Site
                            // add it to the list.

                            Events.add(curEvent);

                        } else if (tagname.equalsIgnoreCase(KEY_ID)) {
                            // if </name> use setName() on curSite
                            curEvent.setEventID(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_NAME)) {
                            // if </link> use setLink() on curSite
                            curEvent.setEvntName(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_DATE)) {
                            // if </about> use setAbout() on curSite
                            curEvent.setEvntStartDate(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_TIME)) {
                            // if </image> use setImgUrl() on curSite
                            curEvent.setEvntStartTime(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_LOCATION)) {
                            // if </name> use setName() on curSite
                            curEvent.setEvntLocation(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_DESC)) {
                            // if </link> use setLink() on curSite
                            curEvent.setEvntDesc(curText);
                        } else if (tagname.equalsIgnoreCase(KEY_CATNAME)) {
                            // if </about> use setAbout() on curSite
                            curEvent.setCatname(curText);
                            Bitmap bMap ;
                            if (curText.equalsIgnoreCase("arts")){
                                 bMap= BitmapFactory.decodeResource(ctx.getResources(), R.drawable.artbg);
                            }
                            else if(curText.equalsIgnoreCase("Academics")){
                                 bMap= BitmapFactory.decodeResource(ctx.getResources(), R.drawable.academicsbg);
                            }
                            else if(curText.equalsIgnoreCase("Athletics")){
                                 bMap= BitmapFactory.decodeResource(ctx.getResources(), R.drawable.sportbg);
                            }
                            else {
                                 bMap= BitmapFactory.decodeResource(ctx.getResources(), R.drawable.othersbg);
                            }

                            curEvent.setBitmap(bMap);
                        } else if (tagname.equalsIgnoreCase(KEY_IMAGE_URL)) {
                            // if </image> use setImgUrl() on curSite
                            curEvent.setEvntImgURL(curText);
                        }else if (tagname.equalsIgnoreCase(KEY_lIKES)) {
                            // if </image> use setImgUrl() on curSite
                            curEvent.setLikes(curText);
                        }
                        break;

                    default:
                        break;
                }
                //move on to next iteration
                eventType = xpp.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // return the populated list.
        return Events;
    }
}
