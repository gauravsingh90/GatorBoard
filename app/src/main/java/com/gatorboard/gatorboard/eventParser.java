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
 //commit
                JSONObject obj = ar.getJSONObject(i);
                Event event = new Event(1,"a","a");

                event.setEventId(obj.getInt("eventId"));
                event.setEventName(obj.getString("eventName"));
                event.setEventDesc(obj.getString("eventDesc"));
                event.setPhoto(obj.getString("photo"));

                //setting bitmap image from url
                try {
                    String imageUrl = event.getPhoto();
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    event.setBitmap(bitmap);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                eventList.add(event);
            }

            return eventList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }
}