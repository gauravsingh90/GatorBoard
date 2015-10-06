package com.gatorboard.gatorboard;

/**
 * Created by sushma on 10/6/2015.
 */
public class Event {
    private int EventId;
    private String EventName;
    private String EventDesc;

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

    @Override
    public String toString(){
        return EventName;
    }
}
