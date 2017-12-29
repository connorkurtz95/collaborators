package com.example.ckurtz8533.collaborators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.Toast;
import java.util.List;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends AppCompatActivity
        implements OnEditorActionListener {

    private AppDatabase database;

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

        username.setOnEditorActionListener((TextView.OnEditorActionListener) this);

        database = AppDatabase.getDatabase(getApplicationContext());

        // cleanup for testing some initial data
        //database.userDao().removeAllUsers();
        // add some data

        List<User> users = database.userDao().getAllUser();

        if (users.size()==0) {
            database.userDao().addUser(new User(1, "username", "password", "email"));
            Toast.makeText(this, "default user created.", Toast.LENGTH_SHORT).show();
        }

        int currentID = database.currentUserDao().getId();

        if(currentID != 0)
        {
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
    }

    public void signIn(View view) {
        String usernameText = username.getText().toString();
        String passwordText = password.getText().toString();
        Boolean isChecked = remember.isChecked();

        List<User> users = database.userDao().signInAttempt(usernameText,passwordText);

        if(users.size() != 0)
        {
            database.savedUserDao().removeSavedUser(usernameText);

            if(isChecked)
            {
                database.savedUserDao().addSavedUser(new SavedUser(database.savedUserDao().savedUserMax(),usernameText,passwordText));

                Toast t2 = Toast.makeText(this, "Credentials saved", Toast.LENGTH_SHORT);
                t2.show();
            }

            database.currentUserDao().signIn(new CurrentUser(database.userDao().getUserByUsername(usernameText).id));
            Intent intent = new Intent(this, HomeScreen.class);
            startActivity(intent);
        }
        else
        {
            Toast t2 = Toast.makeText(this, "Incorrect username or password.", Toast.LENGTH_SHORT);
            t2.show();
        }
    }

    public void createAccount(View view) {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        String userPassword = database.savedUserDao().getSavedPassword(username.getText().toString());

        if (userPassword != null && userPassword != "") {
            password.setText(userPassword);
            remember.setChecked(true);
        }

        return false;
    }
}
