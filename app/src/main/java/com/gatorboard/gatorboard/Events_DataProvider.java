package com.gatorboard.gatorboard;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushma on 10/6/2015.
 */
public class Events_DataProvider {
    /*static Context ctx;
    static Bitmap bMap;*/
    private static List<Event> Events = new ArrayList<>();

    public Events_DataProvider(Context context){
        Context ctx = context;
        Bitmap bMap= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.d);
        Bitmap bMap1= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.k);
       // Bitmap bMap2= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.marathon);
        Bitmap bMap3= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.p);
        //Bitmap bMap4= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.recital);
        Bitmap bMap5= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.s);
        Bitmap bMap6= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.s);
        Bitmap bMap7= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.m);
        Event ne = new Event();
        ne.setEventID("1");
        ne.setEvntName("UF Fall BFA Dance Showcase");
        ne.setBitmap(bMap);

        Events.add(ne);


        /*Events.add(new Event(2,"Karillon Recital", evntStartDate, evntStartTime, evntLocation, evntDesc, likes, "A long description for Event 2",bMap1));
        //Events.add(new Event(3,"EcoRun 5K Costume Run","A long description for Event 3",bMap));
        Events.add(new Event(3,"Pop Up Culture", evntStartDate, evntStartTime, evntLocation, evntDesc, likes, "A long description for Event 4",bMap3));
        //Events.add(new Event(5,"String Chamber Ensembles Recital","A long description for Event 5",bMap5));
        Events.add(new Event(4,"Soccer vs LSU", evntStartDate, evntStartTime, evntLocation, evntDesc, likes, "A long description for Event 6",bMap5));
        Events.add(new Event(5,"USTA Regional Championship", evntStartDate, evntStartTime, evntLocation, evntDesc, likes, "A long description for Event 7",bMap6));
        Events.add(new Event(6,"Airwatch CodeDash", evntStartDate, evntStartTime, evntLocation, evntDesc, likes, "A long description for Event 8",bMap7));
        */
    }

    public  List<Event> getEventData(){

        return Events;
    }

}
