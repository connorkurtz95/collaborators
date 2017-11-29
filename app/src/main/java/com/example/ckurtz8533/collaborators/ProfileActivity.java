package com.example.ckurtz8533.collaborators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {

    private AppDatabase database;
    private User user;

    private EditText txtUsername;
    private EditText txtEmail;
    private TextView vUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtUsername = (EditText) findViewById(R.id.txtEditUsername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        vUsername = (TextView) findViewById(R.id.txtViewUsername);

        database = AppDatabase.getDatabase(getApplicationContext());
        user = database.userDao().getUser(database.currentUserDao().getId());

        txtUsername.setText(user.username);
        vUsername.setText(user.username);
        txtEmail.setText(user.email);
    }
    protected void save (View view)
    {
        String userNameString = txtUsername.getText().toString();
        String emailString = txtEmail.getText().toString();

        if(userNameString.compareTo("") == 0 || emailString.compareTo("") == 0)
        {
            Toast t2 = Toast.makeText(this, "Please fill in all information.", Toast.LENGTH_SHORT);
            t2.show();
        }
        else
        {
            database.userDao().updateUser(new User(user.id,userNameString,user.password,emailString));
            Toast t2 = Toast.makeText(this, "Information saved.", Toast.LENGTH_SHORT);
            t2.show();

            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }
}
