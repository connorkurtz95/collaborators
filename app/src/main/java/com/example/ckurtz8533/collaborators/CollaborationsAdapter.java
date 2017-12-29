package com.example.ckurtz8533.collaborators;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by connorkurtz on 12/22/2017.
 */


public class CollaborationsAdapter extends ArrayAdapter<User> {


    public CollaborationsAdapter(Context context, ArrayList<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        User user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_collaboration, parent, false);
        }
        // Lookup view for data population
        TextView tvListText = (TextView) convertView.findViewById(R.id.tvListText);
        // Populate the data into the template view using the data object

        tvListText.setText("Collaboration with " + user.username);
        // Return the completed view to render on screen
        return convertView;
    }
}
