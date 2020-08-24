package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.notes.Note_data;

import java.util.ArrayList;
import java.util.Map;

import static androidx.core.content.ContextCompat.startActivity;

public class Listview_Adapter extends ArrayAdapter<Note_data> {
    private Context mContext;
    int mResource;
    private String title, date;
    private TextView tv_title, tv_date;

    public Listview_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Note_data> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        title = getItem(position).getTitle();
        date = getItem(position).getDate();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        tv_title = convertView.findViewById(R.id.item_title);
        tv_date = convertView.findViewById(R.id.item_date);
        tv_title.setText(title);
        tv_date.setText(date);
        return convertView;
    }
}