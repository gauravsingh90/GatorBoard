package com.gatorboard.gatorboard;

/**
 * Created by Aswini on 10/14/2015.
 */

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.unboundid.ldap.sdk.BindRequest;
import com.unboundid.ldap.sdk.BindResult;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.LDAPException;
import com.unboundid.ldap.sdk.ResultCode;
import com.unboundid.ldap.sdk.SimpleBindRequest;
import com.unboundid.util.ssl.SSLUtil;
import com.unboundid.util.ssl.TrustAllTrustManager;

import org.w3c.dom.Text;

import java.security.GeneralSecurityException;

import javax.net.SocketFactory;

public class Login extends AppCompatActivity{
    LDAPConnection ldapConnection = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_main);
        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // setSupportActionBar(toolbar);


        TextView tx = (TextView) findViewById(R.id.Title);
        Typeface cd = Typeface.createFromAsset(getAssets(), "fonts/century_gothic.ttf");
        tx.setTypeface(cd);

        TextView txClickHere = (TextView) findViewById((R.id.textView19));


        //  myDB = new DatabaseHelper(this);

        Button login = (Button) findViewById(R.id.login);

        final EditText username = (EditText) findViewById(R.id.username);

        final EditText password = (EditText) findViewById(R.id.password);

        final TextView test = (TextView) findViewById(R.id.test);

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        try {


                            final SocketFactory socketFactory;
                            final SSLUtil sslUtil = new SSLUtil(null, new TrustAllTrustManager());
                            socketFactory = sslUtil.createSSLSocketFactory();
                            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                            StrictMode.setThreadPolicy(policy);
                            ldapConnection = new LDAPConnection(socketFactory, "128.227.9.22", 636);
                            final String name = username.getText().toString();
                            final String pass = password.getText().toString();
                            BindRequest bindRequest = new SimpleBindRequest("uid=" + name, pass);

                            BindResult bindResult = ldapConnection.bind(bindRequest);
                            if (bindResult.getResultCode() == ResultCode.SUCCESS) {

                                Intent intent = new Intent(v.getContext(), EventBoard.class);
                                Toast.makeText(getApplicationContext(),"Login Successful.",Toast.LENGTH_LONG).show();
//
                                //intent.putExtra("ufid", name);
//                                startActivity(intent);
                                startActivityForResult(intent, 0);
                                ldapConnection.close();

                            }


                        } catch (LDAPException e) {
                            Toast.makeText(getApplicationContext(),"Incorrect Username/Password.",Toast.LENGTH_LONG).show();
//                            test.setText("Incorrect Username/Password.\nPlease try again!\n\n\n");
//                            test.setEnabled(false);
//                            test.append(e.toString());
                            ldapConnection.close();

                        } catch (GeneralSecurityException exception) {
                            test.setText(exception.toString());
                            ldapConnection.close();
                        }
                    }

                }

        );

        txClickHere.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse("https://login.ufl.edu/idp/Authn/UserPassword"));
//                        goToUrl("https://my.ufl.edu/psp/ps_pwd/EMPLOYEE/EMPL/c/MAINTAIN_SECURITY.UF_PA_FORGOT_PSWD.GBL");
                        startActivity(intent);
            }
        });


    }
}