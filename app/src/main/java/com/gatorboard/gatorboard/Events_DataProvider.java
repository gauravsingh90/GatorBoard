package com.gatorboard.gatorboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sushma on 10/6/2015.
 */
public class Events_DataProvider {
    private static List<Event> Events = new ArrayList<>();
    public static List<Event> getEventData(){

        return Events;
    }

    static {

        Events.add(new Event(1,"Event 1"));
        Events.add(new Event(2,"Event 2"));
        Events.add(new Event(3,"Event 3"));
        Events.add(new Event(4,"Event 4"));
        Events.add(new Event(5,"Event 5"));
        Events.add(new Event(6,"Event 6"));
        Events.add(new Event(7,"Event 7"));
        Events.add(new Event(8,"Event 8"));
        Events.add(new Event(9,"Event 9"));
        Events.add(new Event(10,"Event 10"));
        Events.add(new Event(11,"Event 11"));
        Events.add(new Event(12,"Event 12"));
        Events.add(new Event(13,"Event 13"));
        Events.add(new Event(14,"Event 14"));
        Events.add(new Event(15,"Event 15"));
        Events.add(new Event(16,"Event 16"));
        Events.add(new Event(17,"Event 17"));
        Events.add(new Event(18,"Event 18"));

    }
}
