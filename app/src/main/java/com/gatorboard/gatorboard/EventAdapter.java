package com.gatorboard.gatorboard;

/**
 * Created by sushma on 10/13/2015.
 */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

public class EventAdapter extends ArrayAdapter<Event> implements Filterable {

    private Context context;
    private List<Event> EventList;
    customFilter filter;
    List<Event> filterList;

    public EventAdapter(Context context, int resource, List<Event> objects) {
        super(context, resource, objects);
        this.context = context;
        this.EventList = objects;
        this.filterList = objects;
    }

    @Override
    public int getCount() {
        return EventList.size();
    }

    @Override
    public Event getItem(int position) {
        return EventList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return EventList.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_event,null);
        }
        //View view = inflater.inflate(R.layout.item_event, parent, false);

        //Display Event name in the TextView widget
        Event Event = EventList.get(position);
        TextView tv = (TextView) convertView.findViewById(R.id.textView1);
        tv.setText(Event.getEvntName());



        //Display Event photo in ImageView widget
        ImageView image = (ImageView) convertView.findViewById(R.id.imageView1);
        image.setImageBitmap(Event.getBitmap());

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter = new customFilter();
        }
        return  filter;
    }

    class customFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            if(constraint != null && constraint.length() > 0){
                constraint = constraint.toString().toUpperCase();
                List<Event> filters = new ArrayList<Event>();
                for(int i = 0; i< filterList.size();i++){
                    if(filterList.get(i).getCatname().toUpperCase().contains(constraint) ||  filterList.get(i).getEvntStartDate().equalsIgnoreCase(constraint.toString().trim())){
                        Event e = new Event(filterList.get(i).getEventID(),filterList.get(i).getEvntName(),filterList.get(i).getEvntStartDate(),filterList.get(i).getEvntStartTime(), filterList.get(i).getEvntLocation(), filterList.get(i).getEvntDesc(),filterList.get(i).getLikes(),filterList.get(i).getCatname(),filterList.get(i).getEvntImgURL(),filterList.get(i).getBitmap());
                        filters.add(e);
                    }

                }
                results.count = filters.size();
                results.values = filters;

            }
            else{
                results.count = filterList.size();
                results.values = filterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            EventList = (List<Event>) results.values;
            notifyDataSetChanged();
        }
    }


}

