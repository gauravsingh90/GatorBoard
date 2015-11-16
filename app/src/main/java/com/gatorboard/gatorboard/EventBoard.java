package com.gatorboard.gatorboard;

import android.os.Handler;
import android.util.Log;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.os.AsyncTask;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import android.app.Dialog;
import android.support.v7.widget.Toolbar;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.widget.DatePicker;

public class EventBoard extends AppCompatActivity {

    private DatePicker datePicker;
    private Calendar calendar;
    private TextView dateView;
    private int year, month, day;

    //For floating menu
    private FloatingActionButton fab1;
    private FloatingActionButton fab2;
    private FloatingActionButton fab3;
    private FloatingActionButton fab4;
    private FloatingActionButton fab5;

    private List<FloatingActionMenu> menus = new ArrayList<>();
    private Handler mUiHandler = new Handler();

    private static final String PHOTOS_BASE_URL = "http://services.hanselandpetal.com/photos/";


    ProgressBar pb;
    //List<MyTask> tasks;

    private EventAdapter adapter;
    private ListView listView;
    List<Event> Events = null;
    public static final String EVENT_NAME = "eventName";
    public static final String EVENT_DESC = "eventDesc";
    public static final String EVENT_LIKES = "likes";
    public static final String EVENT_IMAGEURL = "evntImgURL";
    public static final String EVENT_CATEGORY="catname";
    public static final int DETAIL_REQUEST_CODE =  1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("StackSites", "OnCreate()");
        setContentView(R.layout.activity_event_board);

        listView = (ListView) findViewById(android.R.id.list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setLogo(R.drawable.logo32dp);
        toolbar.setTitle("      Gator Board");
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
                        //Toast.makeText(EventBoard.this, "action_settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_share:
                        //Toast.makeText(EventBoard.this, "action_share", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_calender: {
                        //Toast.makeText(EventBoard.this, "action_calender", Toast.LENGTH_SHORT).show();
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


        //tasks = new ArrayList<>();

        if(isNetworkAvailable()){
            Log.i("StackSites", "starting download Task");
            SitesDownloadTask download = new SitesDownloadTask();
            download.execute();
        }else{
            listView = (ListView) findViewById(android.R.id.list);

            adapter = new EventAdapter(getApplicationContext(), -1, SitesXmlPullParser.getEventsFromFile(EventBoard.this));
            listView.setAdapter(adapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Event event =  adapter.getItem(position);
                displayDetail(event);
            }
        });

        //Events_DataProvider Dp = new Events_DataProvider(this);
        //Events =  Dp.getEventData();
        //updateDisplay();

        //Floating Menu
        final FloatingActionMenu menu1 = (FloatingActionMenu) findViewById(R.id.menu1);
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.ic_menu);
        /*final FloatingActionButton programFab1 = new FloatingActionButton(this);
        programFab1.setButtonSize(FloatingActionButton.SIZE_MINI);
        programFab1.setLabelText("Academics");
        programFab1.setImageResource(R.drawable.ic_edit);
        menu1.addMenuButton(programFab1);
        programFab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(EventBoard.this, programFab1.getLabelText(), Toast.LENGTH_SHORT).show();
            }
        });*/

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
        fab4 = (FloatingActionButton) findViewById(R.id.fab4);
        fab5 = (FloatingActionButton) findViewById(R.id.fab5);

        //fab1.setEnabled(false);

        fab1.setOnClickListener(clickListener);
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
        fab4.setOnClickListener(clickListener);
        fab5.setOnClickListener(clickListener);
        //menu1.toggle(true);



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
    /*public void startWorking(){
        if (isOnline()) {

            System.out.println("finally here");
            requestData("http://services.hanselandpetal.com/secure/flowers.json");
        } else {
            System.out.println("inside offline");
            Toast.makeText(this, "Network is not available", Toast.LENGTH_LONG).show();
        }
    }*/


    /*private void requestData(String uri) {

        urlRequester p = new urlRequester();
        p.setMethod("GET");
        p.setUri(uri);
       // p.setParam("name", "Rosa");

        MyTask task = new MyTask();
        task.execute(p);
    }*/

    protected void updateDisplay() {


        adapter = new EventAdapter(this, R.layout.item_event, Events);
        listView.setAdapter(adapter); //setListAdapter(adapter);


    }

    private void displayDetail(Event event) {
        Log.d("activity_event_board", "Displaying Event: " + event.getEvntName());
        Intent intent = new Intent(this, Event_Details.class);
        intent.putExtra(EVENT_NAME,event.getEvntName());
        intent.putExtra(EVENT_DESC,event.getEvntDesc());
        intent.putExtra(EVENT_LIKES,event.getLikes());
        intent.putExtra(EVENT_IMAGEURL,event.getEvntImgURL());
        intent.putExtra(EVENT_CATEGORY,event.getCatname());
        startActivityForResult(intent,DETAIL_REQUEST_CODE );

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

   /* private class MyTask extends AsyncTask<urlRequester, String,  List<Event>> {

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

    }*/

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
                    break;
                case R.id.fab3:
                    text = fab3.getLabelText();
                    break;
                case R.id.fab4:
                    text = fab4.getLabelText();
                    break;
                case R.id.fab5:
                    text = fab5.getLabelText();
                    break;
            }

            //Toast.makeText(EventBoard.this, text, Toast.LENGTH_SHORT).show();
            if (text.equalsIgnoreCase("All")){
                text = null;
            }
            adapter.getFilter().filter(text);
            final FloatingActionMenu menu1 = (FloatingActionMenu) findViewById(R.id.menu1);
            menu1.toggle(true);


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
        StringBuilder datepicker=new StringBuilder().append(month).append("/")
                .append(day).append("/").append(year);
        //Toast.makeText(EventBoard.this, datepicker.toString(), Toast.LENGTH_SHORT).show();
        adapter.getFilter().filter(datepicker.toString());
    }

    private class SitesDownloadTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... arg0) {
            //Download the file
            try {
                Downloader.DownloadFromUrl("https://dl.dropboxusercontent.com/u/58872088/event.xml", openFileOutput("event.xml", Context.MODE_PRIVATE));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            //setup our Adapter and set it to the ListView.
            /*SitesXmlPullParser Dpr = new SitesXmlPullParser();*/
            Events = SitesXmlPullParser.getEventsFromFile(EventBoard.this);
            adapter = new EventAdapter(EventBoard.this, -1, Events);
            listView.setAdapter(adapter);
            Log.i("StackSites", "adapter size = "+ adapter.getCount());
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}