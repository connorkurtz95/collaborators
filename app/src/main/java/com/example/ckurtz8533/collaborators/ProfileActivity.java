package com.example.ckurtz8533.collaborators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private EditText screenName;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        screenName = (EditText) findViewById(R.id.txtScreenName);
        email = (EditText) findViewById(R.id.txtEmail);
    }
    protected void save (View view)
    {
        String screenNameString = screenName.getText().toString();
        String emailString = email.getText().toString();

        if(screenNameString.compareTo("") == 0 || emailString.compareTo("") == 0)
        {
            Toast t2 = Toast.makeText(this, "Please fill in all information.", Toast.LENGTH_SHORT);
            t2.show();
        }
        else
        {
            Toast t2 = Toast.makeText(this, "Information saved.", Toast.LENGTH_SHORT);
            t2.show();

            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }
}
