package com.gatorboard.gatorboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Event_Details extends AppCompatActivity {

    protected String EventName;
    protected String EventDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        EventName = getIntent().getStringExtra(EventBoard.EVENT_TITLE);
        TextView eTitle = (TextView) findViewById(R.id.eventID);
        eTitle.setText(EventName);
        EventDesc = getIntent().getStringExtra(EventBoard.EVENT_DESC);
        TextView eDesc = (TextView) findViewById(R.id.eventDesc);
        eDesc.setText(EventDesc);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event__details, menu);
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
