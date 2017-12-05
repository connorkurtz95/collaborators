package com.example.ckurtz8533.collaborators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class StartCollaboration extends AppCompatActivity {

    private AppDatabase database;

    private EditText txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_collaboration);

        database = AppDatabase.getDatabase(getApplicationContext());

        txtUsername = (EditText) findViewById(R.id.txtUsername);
    }

    public void search(View view) {
        String userNameString = txtUsername.getText().toString();

        User user = database.userDao().getUserByUsername(userNameString);

        if(user == null)
        {
            Toast t3 = Toast.makeText(this, userNameString + " does not exist.", Toast.LENGTH_SHORT);
            t3.show();
        }
        else if (userNameString.compareTo(database.userDao().getUser(database.currentUserDao().getId()).username) == 0)
        {
            Toast t3 = Toast.makeText(this, "You can not collaborate with yourself.", Toast.LENGTH_SHORT);
            t3.show();
        }
        else
        {
            Toast t3 = Toast.makeText(this, userNameString + " exists, but this function does not work yet.", Toast.LENGTH_SHORT);
            t3.show();
        }
    }
}