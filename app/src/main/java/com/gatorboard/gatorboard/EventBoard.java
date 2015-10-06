package com.gatorboard.gatorboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class EventBoard extends AppCompatActivity {

    public static final String EVENT_TITLE = "eventTITLE";
    public static final String EVENT_DESC = "eventDESC";
    public static final int EVENT_ID = 1;

    protected List<Event> Events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_board);


        Events = Events_DataProvider.getEventData();
        ArrayAdapter<Event> EventAdapter = new ArrayAdapter<Event>(this,android.R.layout.simple_expandable_list_item_1,Events);
        ListView listview = (ListView) findViewById(android.R.id.list);
        listview.setAdapter(EventAdapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = Events.get(position);
                displayDetail(event);
            }
        });



        /*Iterator<Event> iterator = Events.iterator();
        StringBuilder builder = new StringBuilder();
        while(iterator.hasNext()){
            Event event = iterator.next();
            builder.append(event.getEventId()).append(" ").append(event.getEventDesc()).append("\n");
        }
        TextView tv = (TextView) findViewById(R.id.EventHolder);
        tv.setText(builder.toString());*/
    }

    private void displayDetail(Event event) {
        //Log.d("MainActivity", "Displaying Event: "+ event.getEventDesc());
        Intent intent = new Intent(this,Event_Details.class);
        intent.putExtra(EVENT_TITLE, event.getEventName());
        intent.putExtra(EVENT_DESC, event.getEventDesc());
        startActivityForResult(intent, EVENT_ID);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_board, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
