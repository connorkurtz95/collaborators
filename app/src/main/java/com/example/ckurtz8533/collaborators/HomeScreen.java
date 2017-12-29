package com.example.ckurtz8533.collaborators;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    private AppDatabase database;
    private User user;

    private TextView txtUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        database = AppDatabase.getDatabase(getApplicationContext());
        user = database.userDao().getUser(database.currentUserDao().getId());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        txtUsername = (TextView) findViewById(R.id.txtHomeUsername);
        txtUsername.setText(user.username);

        List<User> collaboratingUsers = database.collaborationDao().getCollaborationUsers(user.id);
        ArrayList<User> arrayOfUsers = new ArrayList<User>();
        CollaborationsAdapter adapter = new CollaborationsAdapter(this,arrayOfUsers);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        adapter.addAll(collaboratingUsers);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

                String item = ((TextView)view).getText().toString();

                String eUsername = item.substring(19);

                Intent intentBundle = new Intent (HomeScreen.this, StartedCollaboration.class);
                intentBundle.putExtra("username",eUsername);
                startActivity(intentBundle);
            }
        });
    }

    public void signOut(View view) {
        database.currentUserDao().signOut();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        Intent mServiceIntent = new Intent(this, CounterIntentService.class);
        this.startService(mServiceIntent);
    }

    public void profile(View view) {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void startCollaboration(View view) {
        Intent intent = new Intent(this, StartCollaboration.class);
        startActivity(intent);
    }

    public void listButton(View view) {
        Intent intent = new Intent(this, StartCollaboration.class);
        startActivity(intent);
    }
}
