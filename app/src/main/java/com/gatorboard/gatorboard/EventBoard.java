package com.gatorboard.gatorboard;

import android.os.Handler;
import android.util.Log;
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
import android.widget.ImageView;
import android.widget.ListView;
import android.app.Activity;
import android.os.AsyncTask;
import android.app.ListActivity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.PrintWriter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.gatorboard.gatorboard.urlRequester;
import com.gatorboard.gatorboard.urlConnectionManager;
import com.gatorboard.gatorboard.eventParser;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.List;
import android.app.Dialog;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;

import java.util.Calendar;
import java.sql.Date;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class EventBoard extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    //For floating menu
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;

    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();

    private static final String PHOTOS_BASE_URL = "http://services.hanselandpetal.com/photos/";


    ProgressBar pb;
    List<MyTask> tasks;


    List<Event> Events = null;
    public static final String EVENT_NAME = "eventName";
    public static final int DETAIL_REQUEST_CODE =  1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_board);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setLogo(R.drawable.logo30);
        toolbar.setTitle(" Gator Board");
        setSupportActionBar(toolbar);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(EventBoard.this, "action_settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_share:
                        Toast.makeText(EventBoard.this, "action_share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_calender: {
                        Toast.makeText(EventBoard.this, "action_calender", Toast.LENGTH_SHORT).show();
                        showDialog(999);
                    }
                    default:
                        break;
                }
                return true;
            }
        });

        pb = (ProgressBar) findViewById(R.id.progressBar1);
        pb.setVisibility(View.INVISIBLE);


        tasks = new ArrayList<>();
        Events_DataProvider Dp = new Events_DataProvider(this);
        Events =  Dp.getEventData();
        updateDisplay();

        //Floating Menu
        final FloatingActionMenu menu1 = (FloatingActionMenu) findViewById(R.id.menu1);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_menu);
        final FloatingActionButton programFab1 = new FloatingActionButton(this);
        programFab1.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab1.setLabelText("Academics");
        programFab1.setImageResource(R.drawable.ic_edit);
        menu1.addMenuButton(programFab1);
        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventBoard.this, programFab1.getLabelText(), Toast.LENGTH_SHORT).show();
            }
        });

        menu1.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (menu1.isOpened()) {
                    //Toast.makeText(EventBoard.this, menu1.getMenuButtonLabelText(), Toast.LENGTH_SHORT).show();
                }
                menu1.toggle(true);
            }
        });

        menus.add(menu1);
        menu1.hideMenuButton(false);

        int delay = 400;
        for (final FloatingActionMenu menu : menus) {
            mUiHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    menu.showMenuButton(true);
                }
            }, delay);
            delay += 150;
        }

        menu1.setClosedOnTouchOutside(true);

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        //fab1.setEnabled(false);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);



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
       //this.startWorking();
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
       // p.setParam("name", "Rosa");

        MyTask task = new MyTask();
        task.execute(p);
    }

    protected void updateDisplay() {

        ListView listView = (ListView) findViewById(android.R.id.list);
        EventAdapter adapter = new EventAdapter(this, R.layout.item_event, Events);
        listView.setAdapter(adapter); //setListAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event = Events.get(position);
                displayDetail(event);
            }
        });

    }

    private void displayDetail(Event event) {
        Log.d("activity_event_board", "Displaying Event: " + event.getEventName());
        Intent intent = new Intent(this, Event_Details.class);
        intent.putExtra(EVENT_NAME,event.getEventName());
        startActivityForResult(intent,DETAIL_REQUEST_CODE );

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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

    //For floating Menu
    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String text = "";

            switch (v.getId()) {
                case R.id.fab1:
                    text = fab1.getLabelText();
                    break;
                case R.id.fab2:
                    text = fab2.getLabelText();
                    //fab2.setVisibility(View.GONE);
                    break;
                case R.id.fab3:
                    text = fab3.getLabelText();
                    fab2.setVisibility(View.VISIBLE);
                    break;
            }

            Toast.makeText(EventBoard.this, text, Toast.LENGTH_SHORT).show();
        }
    };

    protected Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
            showDate(arg1, arg2 + 1, arg3);
        }
    };

    private void showDate(int year, int month, int day) {
        StringBuilder datepicker=new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year);
        Toast.makeText(EventBoard.this, datepicker, Toast.LENGTH_SHORT).show();
    }


}