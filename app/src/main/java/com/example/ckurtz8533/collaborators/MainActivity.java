package com.example.ckurtz8533.collaborators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String rememberUsername = "";
    private String rememberPassword = "";
    private EditText username;
    private EditText password;
    private CheckBox remember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.txtUsername);
        password = (EditText) findViewById(R.id.txtPassword);
        remember = (CheckBox) findViewById(R.id.chkRemember);

        if(rememberUsername.compareTo("") != 0)
        {
            username.setText(rememberUsername);
            password.setText(rememberPassword);
            remember.setChecked(true);
        }
    }

    public void signIn(View view) {
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        Boolean isChecked = remember.isChecked();
        if(usernameText.compareTo("username") == 0 && passwordText.compareTo("password") == 0)
        {
            if(isChecked)
            {
                rememberUsername = usernameText;
                rememberPassword = passwordText;

                Toast t2 = Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT);
                t2.show();
            }
            else
            {
                rememberUsername = "";
                rememberPassword = "";
            }

            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
        else
        {
            Toast t2 = Toast.makeText(this, "Incorrect username or password.", Toast.LENGTH_SHORT);
            t2.show();
        }
    }
}
