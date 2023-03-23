package com.example.quezapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import Dao.Class.Categorie;

public class CategorieAdapterSpinner extends BaseAdapter {
    ArrayList<Categorie> lst;
    LayoutInflater layoutInflater;
    public CategorieAdapterSpinner(Context context , ArrayList<Categorie> lst){
        this.lst=lst;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return lst.size();
    }

    public int getPosition(int Id)
    {
        int position = -1;
        for(int counter = 0 ;counter< lst.size(); counter++){
            if(getItemId(counter)==Id){
                position=counter;
            }
        }
        return position;
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
