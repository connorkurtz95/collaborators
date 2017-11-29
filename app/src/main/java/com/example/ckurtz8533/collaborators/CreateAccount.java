package com.example.ckurtz8533.collaborators;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccount extends AppCompatActivity {

    private AppDatabase database;
    private User user;

    private EditText txtUsername;
    private EditText txtEmail;
    private EditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        database = AppDatabase.getDatabase(getApplicationContext());

        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
    }
    protected void save (View view)
    {
        String userNameString = txtUsername.getText().toString();
        String emailString = txtEmail.getText().toString();
        String passwordString = txtPassword.getText().toString();

        User usernameUser = database.userDao().getUserByUsername(userNameString);
        User emailUser = database.userDao().getUserByEmail(emailString);

        if(userNameString.compareTo("") == 0 || emailString.compareTo("") == 0 || passwordString.compareTo("") == 0)
        {
            Toast t2 = Toast.makeText(this, "Please fill in all information.", Toast.LENGTH_SHORT);
            t2.show();
        }
        else if(usernameUser != null)
        {
            Toast t2 = Toast.makeText(this, "This username already exists.", Toast.LENGTH_SHORT);
            t2.show();
        }
        else if (emailUser != null)
        {
            Toast t2 = Toast.makeText(this, "This email already exists.", Toast.LENGTH_SHORT);
            t2.show();
        }
        else
        {
            database.userDao().addUser(new User(database.userDao().userMax()+1, userNameString, passwordString, emailString));
            Toast t2 = Toast.makeText(this, "Information saved.", Toast.LENGTH_SHORT);
            t2.show();

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
}
