package com.example.quezapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterSpinner extends BaseAdapter {
    ArrayList<String> lst;
    LayoutInflater layoutInflater;
    public AdapterSpinner(Context context , ArrayList<String> lst){
        this.lst=lst;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lst.size();
    }



    @Override
    public Object getItem(int position) {
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view = layoutInflater.inflate(R.layout.spinner_v,null);
        TextView textView = view.findViewById(R.id.spinner_title);
        textView.setText(lst.get(position));
        return view;
    }
}
