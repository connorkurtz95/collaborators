package com.example.ckurtz8533.collaborators;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;

public class HomeScreen extends AppCompatActivity {

    private AppDatabase database;
    private User user;

    private TextView txtUsername;
    private TextView txtCollaborations1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        database = AppDatabase.getDatabase(getApplicationContext());
        user = database.userDao().getUser(database.currentUserDao().getId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        txtUsername = (TextView) findViewById(R.id.txtHomeUsername);
        txtUsername.setText(user.username);

        txtCollaborations1 = (TextView) findViewById(R.id.txtCollaborations1);
        txtCollaborations1.setText("You have no open collaborations.");
    }

    public void signOut(View view) {
        database.currentUserDao().signOut();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void startCollaboration(View view) {
        Intent intent = new Intent(this, StartCollaboration.class);
        startActivity(intent);
    }

}
