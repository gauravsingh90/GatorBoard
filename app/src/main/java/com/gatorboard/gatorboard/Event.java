package com.gatorboard.gatorboard;

/**
 * Created by sushma on 10/6/2015.
 */
public class Event {
    private int EventId;
    private String EventDesc;

    public Event(int EId, String EDesc)
    {
        EventId = EId;
        EventDesc = EDesc;
    }

    public int getEventId()
    {
        return EventId;
    }

    public String getEventDesc()
    {

        return EventDesc;
    }

    @Override
    public String toString(){
        return EventDesc;
    }
}
