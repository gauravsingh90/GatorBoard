package com.gatorboard.gatorboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tv2 = (TextView) findViewById(R.id.textView);
        tv2.setText("GatorBoard is a hyper local Android application which helps its users categorize and collect event information for events happening in the University of Florida community.  It also provides reminders for the events and is intended for UF students.\n" +
                "\n\tThe GatorBoard system is composed of two main components: a client-side application which will run on Android handsets and provide event information to users, and a server-side application which will be a Web App that will provide an interface for event organizers to update the event information. The system is designed to gather information regarding events happening in and around the University of Florida and present this information in an organized manner to users on the go.");

    }

}
