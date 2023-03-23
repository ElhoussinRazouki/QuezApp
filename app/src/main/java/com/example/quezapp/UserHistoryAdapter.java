package com.example.quezapp;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import Dao.Class.EvalClass;
import Dao.Class.Evaluation;
import Dao.Db.Evaluation_Dao;

public class UserHistoryAdapter extends BaseAdapter {
    TextView txtCategorie ;
    TextView txtLevel ;
    TextView txtdate ;
    TextView txtScore;
    LayoutInflater layoutInflater;
    ArrayList<EvalClass> listEva;

    public UserHistoryAdapter(Context context , ArrayList<EvalClass> lstEval) {
        layoutInflater = LayoutInflater.from(context);
        this.listEva = lstEval;
    }


    @Override
    public int getCount() {
        return listEva.size();
    }

    @Override
    public Object getItem(int position) {
        return listEva.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listEva.get(position).getIdEval();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.history_evaliation_layout,null);
        Log.i("test","adapter in");
        txtCategorie = view.findViewById(R.id.txt_user_history_category);
        txtLevel = view.findViewById(R.id.txt_user_history_level);
        txtdate = view.findViewById(R.id.txt_user_history_date);
        txtScore = view.findViewById(R.id.txt_user_history_score);

        txtCategorie.setText(listEva.get(position).getLevel());
        txtLevel.setText(listEva.get(position).getCategorie());
        txtdate.setText(listEva.get(position).getDate());
        txtScore.setText(listEva.get(position).getScore()+" %");
        return view;
    }
}
