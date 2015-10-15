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
        Bitmap bMap= BitmapFactory.decodeResource(ctx.getResources(),R.drawable.logo1);
        Events.add(new Event(1,"Event 1","A long description for Event 1",bMap));
        Events.add(new Event(2,"Event 2","A long description for Event 2",bMap));
        Events.add(new Event(3,"Event 3","A long description for Event 3",bMap));
        Events.add(new Event(4,"Event 4","A long description for Event 4",bMap));
        Events.add(new Event(5,"Event 5","A long description for Event 5",bMap));
        Events.add(new Event(6,"Event 6","A long description for Event 6",bMap));
        Events.add(new Event(7,"Event 7","A long description for Event 7",bMap));
        Events.add(new Event(8,"Event 8","A long description for Event 8",bMap));
        Events.add(new Event(9,"Event 9","A long description for Event 9",bMap));
        Events.add(new Event(10,"Event 10","A long description for Event 10",bMap));
        Events.add(new Event(11,"Event 11","A long description for Event 11",bMap));
        Events.add(new Event(12,"Event 12","A long description for Event 12",bMap));
        Events.add(new Event(13,"Event 13","A long description for Event 13",bMap));
        Events.add(new Event(14,"Event 14","A long description for Event 14",bMap));
        Events.add(new Event(15,"Event 15","A long description for Event 15",bMap));
        Events.add(new Event(16,"Event 16","A long description for Event 16",bMap));
        Events.add(new Event(17,"Event 17","A long description for Event 17",bMap));
        Events.add(new Event(18,"Event 18","A long description for Event 18",bMap));
    }

    public  List<Event> getEventData(){

        return Events;
    }

   /* static {

        Events.add(new Event(1,"Event 1","A long description for Event 1",bMap));
        Events.add(new Event(2,"Event 2","A long description for Event 2",bMap));
        Events.add(new Event(3,"Event 3","A long description for Event 3",bMap));
        Events.add(new Event(4,"Event 4","A long description for Event 4",bMap));
        Events.add(new Event(5,"Event 5","A long description for Event 5",bMap));
        Events.add(new Event(6,"Event 6","A long description for Event 6",bMap));
        Events.add(new Event(7,"Event 7","A long description for Event 7",bMap));
        Events.add(new Event(8,"Event 8","A long description for Event 8",bMap));
        Events.add(new Event(9,"Event 9","A long description for Event 9"));
        Events.add(new Event(10,"Event 10","A long description for Event 10"));
        Events.add(new Event(11,"Event 11","A long description for Event 11"));
        Events.add(new Event(12,"Event 12","A long description for Event 12"));
        Events.add(new Event(13,"Event 13","A long description for Event 13"));
        Events.add(new Event(14,"Event 14","A long description for Event 14"));
        Events.add(new Event(15,"Event 15","A long description for Event 15"));
        Events.add(new Event(16,"Event 16","A long description for Event 16"));
        Events.add(new Event(17,"Event 17","A long description for Event 17"));
        Events.add(new Event(18,"Event 18","A long description for Event 18"));*//*

    }*/
}
