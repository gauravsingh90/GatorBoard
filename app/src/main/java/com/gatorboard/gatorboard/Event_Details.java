package com.gatorboard.gatorboard;

import android.app.ListActivity;
import java.util.HashMap;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.widget.Toast;
import com.gatorboard.gatorboard.urlRequester;
import com.gatorboard.gatorboard.urlConnectionManager;
import com.gatorboard.gatorboard.eventParser;
import com.gatorboard.gatorboard.eventDescParser;
import com.gatorboard.gatorboard.EventDesc;
import com.gatorboard.gatorboard.LikedEvents;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Event_Details extends ListActivity {

    protected String EventName;
    protected String EventDesc;
    protected String Elikes;
    protected String Ecategory;
    protected String EventStartTime;
    protected String EventStartDate;
    protected String Eimages;



    List<String> myList = new ArrayList<String>();


    public int counter =0;
    HashMap<String, String> meMap=new HashMap<String, String>();


    //progress bar and mytask for webservice
    /*ProgressBar pb;
    List<MyTask> tasks;
    //resolve error add to xml
    tasks = new ArrayList<>();

    pb = (ProgressBar) findViewById(R.id.progressBar1);
    pb.setVisibility(View.INVISIBLE);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

//LOCATION BUTTON LISTENER
      /*  final Button location = (Button) findViewById(R.id.locationbutton);

        location.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


            }
        });*/



        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_event_details);
        EventName = getIntent().getStringExtra(EventBoard.EVENT_NAME);
        TextView eTitle = (TextView) findViewById(R.id.txtStoreName);
        eTitle.setText(EventName);
        EventDesc = getIntent().getStringExtra(EventBoard.EVENT_DESC);
        TextView eDesc = (TextView) findViewById(R.id.textView);
        eDesc.setText(EventDesc);
        EventStartDate = getIntent().getStringExtra(EventBoard.EVENT_START_DATE);
        TextView eStartDate = (TextView) findViewById(R.id.textStartDate);
        eStartDate.setText(EventStartDate);
        EventStartTime = getIntent().getStringExtra(EventBoard.EVENT_START_TIME);
        TextView eStartTime = (TextView) findViewById(R.id.textStartTime);
        eStartTime.setText(EventStartTime);
        Ecategory = getIntent().getStringExtra(EventBoard.EVENT_CATEGORY);


        Elikes = getIntent().getStringExtra(EventBoard.EVENT_LIKES);
       TextView eLike = (TextView) findViewById(R.id.likeCount);
        eLike.setText(String.valueOf(Elikes));

        ImageView im = (ImageView) findViewById(R.id.img);

        if ( Ecategory.equalsIgnoreCase("arts")){
            im.setImageResource(R.drawable.art);

        }
        else if(Ecategory.equalsIgnoreCase("Academics")){
            im.setImageResource(R.drawable.academics);

        }
        else if(Ecategory.equalsIgnoreCase("Athletics")){
            im.setImageResource(R.drawable.sport);

        }
        else {
            im.setImageResource(R.drawable.others_image);//replace with original value

        }



        final Button like = (Button) findViewById(R.id.likebutton);

       like.setOnClickListener(new View.OnClickListener() {
           int flag=1;
            @Override

            public void onClick(View v) {

                myList.add(EventName);
                myList.add("test");
                myList.add("test");

                SharedPreferences myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();

                String[] simpleArray = new String[ myList.size() ];
                myList.toArray( simpleArray );
                int[] list = new int[10];

                StringBuilder str = new StringBuilder();
                for (int i = 0; i < myList.size(); i++) {
                    str.append(simpleArray[i]).append(",");
                }
                editor.putString("string", str.toString());
                editor.commit();

                Intent myIntent = new Intent(Event_Details.this, LikedEvents.class);
               //myIntent.putExtras(b);
                Event_Details.this.startActivity(myIntent);

              TextView  eLiket = (TextView) findViewById(R.id.likeCount);
                //incrementing like count


                String tvValue = eLiket.getText().toString();

                if (!tvValue.equals("")) {

                    int num1 = Integer.parseInt(tvValue);
                    num1=num1+1;

                    eLiket.setText(String.valueOf(num1));


                }
                if(flag==1) {
                    like.setEnabled(false);
                    like.setClickable(false);
                    like.setBackgroundResource(R.drawable.like_click);

                    //storage

                    //


                }
                else
                {
                    like.setEnabled(true);
                    like.setClickable(true);
                    like.setBackgroundResource(R.drawable.like);
                }
                flag=0;

            }
        });


        final Button stock = (Button) findViewById(R.id.stockbutton);

        stock.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {

                Intent myIntent = new Intent(Event_Details.this, LikedEvents.class);
                //myIntent.putExtras(b);
                Event_Details.this.startActivity(myIntent);


            }
        });

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

    //webservice call from event description class
    //uncomment if more than webservice from eventboard age is not sufficient to get all data
    /*
    @Override
    protected void onStart() {
        super.onStart();
        this.startWorking();
    }
    public void startWorking(){
        if (isOnline()) {

            //Gaurav to give the webservice URL
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
        //set parameter eventID here
         p.setParam("EventID", "Rosa");

        MyTask task = new MyTask();
        task.execute(p);
    }

    protected void updateDisplay() {

       //update display accordingly

    }






    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private class MyTask extends AsyncTask<urlRequester, String,  EventDesc> {

        @Override
        protected void onPreExecute() {
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }
            tasks.add(this);
        }

        @Override
        protected EventDesc doInBackground(urlRequester... params) {
            EventDesc EventDes = new EventDesc();
            String content = urlConnectionManager.getData(params[0]);
            EventDes = eventDescParser.parseFeedDesc(content);
            return EventDes;

        }

        @Override
        protected void onPostExecute(EventDesc result) {

            updateDisplay();

            tasks.remove(this);
            if (tasks.size() == 0) {
                pb.setVisibility(View.INVISIBLE);
            }

        }

    }
*/

}

