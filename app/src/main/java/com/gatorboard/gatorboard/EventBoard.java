package com.gatorboard.gatorboard;

import android.view.View.OnClickListener;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.io.PrintWriter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.gatorboard.gatorboard.urlRequester;
import com.gatorboard.gatorboard.urlConnectionManager;
import com.gatorboard.gatorboard.eventParser;

import java.util.List;

public class EventBoard extends ListActivity {


    private static final String PHOTOS_BASE_URL =
            "http://services.hanselandpetal.com/photos/";


    ProgressBar pb;
    List<MyTask> tasks;


    protected List<Event> Events;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_board);




        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);




        tasks = new ArrayList<>();


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
    @Override
    protected void onStart() {
        super.onStart();
       this.startWorking();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_event_board, menu);
        return true;
    }




   //discuss format of data
    public void startWorking(){
        if (isOnline()) {

            System.out.println("finally here");
            requestData("http://services.hanselandpetal.com/secure/flowers.json");
        } else {
            System.out.println("inside offline");
            Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
        }
    }


    private void requestData(String uri) {

        urlRequester p = new urlRequester();
        p.setMethod("GET");
        p.setUri(uri);
        p.setParam("name", "Rosa");

        MyTask task = new MyTask();
        task.execute(p);
    }

    protected void updateDisplay() {
        //array adapter sushma
        //output.append(result + "\n");
        //EventAdapter adapter = new EventAdapter(this, R.layout.item_flower, Events);
        //setListAdapter(adapter);
    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    private class MyTask extends AsyncTask<urlRequester, String,  List<Event>> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected List<Event> doInBackground(urlRequester... params) {
            String content = urlConnectionManager.getData(params[0]);
            Events = eventParser.parseFeed(content);
            return Events;

        }

        @Override
        protected void onPostExecute(List<Event> result) {

            updateDisplay();

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }



        }

    }

}