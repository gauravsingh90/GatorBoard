package com.gatorboard.gatorboard;

/**
 * Created by sushma on 10/13/2015.
 */

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event> {

    private Context context;
    private List<Event> EventList;

    public EventAdapter(Context context, int resource, List<Event> objects) {
        super(context, resource, objects);
        this.context = context;
        this.EventList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_event, parent, false);

        //Display Event name in the TextView widget
        Event Event = EventList.get(position);
        TextView tv = (TextView) view.findViewById(R.id.textView1);
        tv.setText(Event.toString());

        //Display Event photo in ImageView widget
        ImageView image = (ImageView) view.findViewById(R.id.imageView1);
        image.setImageBitmap(Event.getBitmap());

        return view;
    }

}

