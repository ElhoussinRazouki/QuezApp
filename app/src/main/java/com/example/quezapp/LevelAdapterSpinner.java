package com.example.quezapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Dao.Class.Categorie;
import Dao.Class.Level;

public class LevelAdapterSpinner extends BaseAdapter {
    ArrayList<Level> lst;
    LayoutInflater layoutInflater;
    public LevelAdapterSpinner(Context context , ArrayList<Level> lst){
        this.lst=lst;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lst.size();
    }

    public int getPosition(int Id)
    {
        int position = 0;
        for(int counter = 0 ;counter< lst.size(); counter++){
            if(lst.get(counter).getId()==Id){
                Log.i("test","yey her is work"+String.valueOf(counter));
                return counter;
            }
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return lst.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lst.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.spinner_v,null);
        TextView textView = view.findViewById(R.id.spinner_title);
        textView.setText(lst.get(position).getTitle());
        return view;
    }
}
