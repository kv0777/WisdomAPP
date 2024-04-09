package com.example.wisdom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SavedThoughtsAdapter extends ArrayAdapter<String> {

    private ArrayList<String> savedThoughtsList;
    private Context context;

    public SavedThoughtsAdapter(Context context, ArrayList<String> savedThoughtsList) {
        super(context, 0, savedThoughtsList);
        this.context = context;
        this.savedThoughtsList = savedThoughtsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.activity_saved_thoughts_item, parent, false);
        }

        String currentThought = savedThoughtsList.get(position);

        TextView thoughtTextView = listItem.findViewById(R.id.thoughtTextView);
        thoughtTextView.setText(currentThought);

        return listItem;
    }
}
