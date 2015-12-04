package com.gatorboard.gatorboard;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class ContactusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus);


        TextView tv2 = (TextView) findViewById(R.id.textView2);
        Typeface cd = Typeface.createFromAsset(getAssets(), "fonts/century_gothic.ttf");
        tv2.setTypeface(cd);

    }

}
