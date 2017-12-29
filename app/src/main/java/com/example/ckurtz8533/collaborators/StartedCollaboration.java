package com.example.ckurtz8533.collaborators;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class StartedCollaboration extends AppCompatActivity {

    private AppDatabase database;

    private TextView txtStartedCollaboration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_collaboration);

        database = AppDatabase.getDatabase(getApplicationContext());

        txtStartedCollaboration = (TextView) findViewById(R.id.txtStartedCollaboration);

        Collaboration newCollaboration = database.collaborationDao().getCollaboration(database.collaborationDao().collaborationMax());

        Intent intentBundle = getIntent();
        Bundle extrasBundle = intentBundle.getExtras();

        if(!extrasBundle.isEmpty())
        {
            txtStartedCollaboration.setText("Collaboration with " + extrasBundle.getString("username"));
        }

        //txtStartedCollaboration.setText("New Collaboration with " + database.userDao().getUser(newCollaboration.secondId).username);
    }

    public void notifyWithMessenger(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_MESSAGING);
        startActivity(intent);
    }

    public void notifyWithEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_EMAIL);
        startActivity(intent);
    }

    public void returnToHome(View view) {
        Intent intent = new Intent(this, HomeScreen.class);
        startActivity(intent);
    }

    public void downloadFile(View view) {
        int x = 0;
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        x);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        String imageUrl = "https://i.pinimg.com/474x/2d/12/5d/2d125d399de8349b171bd61972579e58--pretty-flower-pictures-pretty-flowers.jpg";

        new DownloadFilesTask().execute(imageUrl);
    }

    public DownloadManager downloadManager;

    private long DownloadData (Uri uri, View v) {

        long downloadReference;

        // Create request for android download manager
        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        //Setting title of request
        request.setTitle("Data Download");

        //Setting description of request
        request.setDescription("Android Data download using DownloadManager.");

        //Set the local destination for the downloaded file to a path within the application's external files directory
        request.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DOWNLOADS,"AndroidTutorialPoint.jpg");

        //Enqueue download and save into referenceId
        downloadReference = downloadManager.enqueue(request);

        return downloadReference;
    }
}
