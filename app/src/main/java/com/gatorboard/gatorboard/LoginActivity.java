package com.gatorboard.gatorboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;
import android.content.Context;
import android.view.View.OnClickListener;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = (Button) findViewById(R.id.loginbutton);
        EditText ed1=(EditText)findViewById(R.id.username);
        EditText ed2=(EditText)findViewById(R.id.password);


        login.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                EditText ed1=(EditText)findViewById(R.id.username);
                EditText ed2=(EditText)findViewById(R.id.password);
                if(ed1.getText().toString().equals("Albert Gator") &&

                        ed2.getText().toString().equals("GoGators")) {
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    goToSecondActivity();
                }
                else{
                    //Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Redirecting...",Toast.LENGTH_SHORT).show();
                    goToSecondActivity();

                }


            }

        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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


    private void goToSecondActivity() {

        Intent intent = new Intent(this, EventBoard.class);

        startActivity(intent);
}}
