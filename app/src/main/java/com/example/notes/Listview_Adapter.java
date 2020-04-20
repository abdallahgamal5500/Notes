package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

    public Listview_Adapter(@NonNull Context context, int resource, @NonNull ArrayList<Note_data> objects) {
        super(context, resource, objects);
        mContext =context;
        mResource=resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String title=getItem(position).getTitle();
        String text=getItem(position).getText();
        String id=getItem(position).getId();
        String date=getItem(position).getDate();
        Note_data note_data =new Note_data(title,text,id,date);
        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView =inflater.inflate(mResource,parent,false);
        TextView tv_title=convertView.findViewById(R.id.item_title);
        TextView tv_date=convertView.findViewById(R.id.item_date);
        tv_title.setText(title);
        tv_date.setText(date);
        return convertView;
    }}