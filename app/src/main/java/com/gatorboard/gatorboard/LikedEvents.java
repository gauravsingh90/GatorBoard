package com.gatorboard.gatorboard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;


public class LikedEvents extends AppCompatActivity {


    List<String> likList = new ArrayList<String>();
    private List<String> issues = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Bundle b=this.getIntent().getExtras();
        //String[] array1=b.getStringArray("key");

        //SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("myPrefs", MODE_PRIVATE);


        String savedString = sharedPreferences.getString("string", "");
        StringTokenizer st = new StringTokenizer(savedString, ",");
        System.out.println("string passed");
        System.out.println(savedString);
        String [] items = savedString.split(",");
        List<String> container = Arrays.asList(items);




        setContentView(R.layout.activity_liked_events);
        ListView likelist = (ListView) findViewById(R.id.likelist);
       // List<String> your_array_list = new ArrayList<String>();
        //your_array_list.add("foo");
        //your_array_list.add("bar");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                container);

        likelist.setAdapter(arrayAdapter);
        //super.onBackPressed();
        final Button back = (Button) findViewById(R.id.backbutton);

        back.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {

                finish();
                //super.onBackPressed();


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_liked_events, menu);
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
    public void showTitle() {
        try {
            ((View) findViewById(android.R.id.title).getParent())
                    .setVisibility(View.VISIBLE);
        } catch (Exception e) {
        }
        getWindow().addFlags(
                WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
