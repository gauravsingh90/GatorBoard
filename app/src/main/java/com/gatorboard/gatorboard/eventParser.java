package com.gatorboard.gatorboard;

/**
 * Created by Aswini on 10/7/2015.
 */


        import java.util.ArrayList;
        import java.util.List;
        import java.io.InputStream;
        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import java.net.URL;

        import com.gatorboard.gatorboard.Event;

public class eventParser {

    public static List<Event> parseFeed(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            List<Event> eventList = new ArrayList<>();

            for (int i = 0; i < ar.length(); i++) {

                JSONObject obj = ar.getJSONObject(i);
                Event event = new Event();
                System.out.print("here here");
                event.setEventId(obj.getInt("eventID"));
                event.setEventName(obj.getString("evntName"));
                event.setCategoryID(obj.getString("catID"));
                //photo to be displayed in eventlist .This is to be replaced with offline images with starting letter of category
                //event.setPhoto(obj.getString("evntImgURL"));

                //setting bitmap image from url
                //This is to be replaced with offline images with starting letter of category
                /*try {
                    String imageUrl = event.getPhoto();
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    event.setBitmap(bitmap);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/


                //setting extra fields in event class to reduce number of webservices

               /* event.setEvntDesc(obj.getString("evntDesc"));
                event.setEvntStartDate(obj.getString("evntStartDate"));
                event.setEvntEndDate(obj.getString("evntEndDate"));
                event.setEvntStartTime(obj.getString("evntStartTime"));
                event.setEvntEndTime(obj.getString("EvntEndTime"));
                event.setEvntImgURL(obj.getString("evntImgURL"));
                event.setEvntLocation(obj.getString("evntLocation"));
                event.setEvntTags(obj.getString("evntTags"));

                //setting bitmap image from image in eventdesc page
                try {
                    String imageUrl = event.getEvntImgURL();
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    event.setBitmap(bitmap);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                eventList.add(event);
            }

            return eventList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;

    }
    }
}