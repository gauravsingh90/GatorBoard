package com.gatorboard.gatorboard;

import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import java.net.URL;
import com.gatorboard.gatorboard.EventDesc;


/**
 * Created by Aswini on 11/4/2015.
 */
public class eventDescParser {


        public static EventDesc parseFeedDesc(String content) {

        try {
            JSONArray ar = new JSONArray(content);
            JSONObject obj = ar.getJSONObject(0);
            //EventDesc eventd = new EventDesc(1,"a","a","a","a","a","a","a","a","a","a");
            EventDesc eventd = new EventDesc();


            eventd.setEventId(obj.getInt("eventID"));
            eventd.setEventName(obj.getString("evntName"));
            eventd.setEvntDesc(obj.getString("evntDesc"));
            eventd.setEvntStartDate(obj.getString("evntStartDate"));
            eventd.setEvntEndDate(obj.getString("evntEndDate"));
            eventd.setEvntStartTime(obj.getString("evntStartTime"));
            eventd.setEvntEndTime(obj.getString("EvntEndTime"));
            eventd.setEvntImgURL(obj.getString("evntImgURL"));
            eventd.setEvntLocation(obj.getString("evntLocation"));
            eventd.setEvntTags(obj.getString("evntTags"));



                //setting bitmap image from url lazyloading image
                try {
                    String imageUrl = eventd.getEvntImgURL();
                    InputStream in = (InputStream) new URL(imageUrl).getContent();
                    Bitmap bitmap = BitmapFactory.decodeStream(in);
                    eventd.setBitmap(bitmap);
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }





            return eventd;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

}
