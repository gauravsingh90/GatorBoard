package com.gatorboard.gatorboard;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class Event_Details extends ListActivity {

    protected String EventName;
    protected String EventDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);
        EventName = getIntent().getStringExtra(EventBoard.EVENT_NAME);
        TextView eTitle = (TextView) findViewById(R.id.txtStoreName);
        eTitle.setText(EventName);
        EventDesc = getIntent().getStringExtra(EventBoard.EVENT_DESC);
        TextView eDesc = (TextView) findViewById(R.id.textView);
        eDesc.setText(EventDesc);
        //eDesc.setMovementMethod(new ScrollingMovementMethod());*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_details, menu);
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
