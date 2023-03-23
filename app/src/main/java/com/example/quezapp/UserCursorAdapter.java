package com.example.quezapp;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import Dao.Db.Categorie_Dao;
import Dao.Db.Level_Dao;

public class UserCursorAdapter extends CursorAdapter {
    EditText txtLevel;
    Button btnUpdate , btnDelete;
    Level_Dao level_dao ;
    public UserCursorAdapter(Context context, Cursor c, boolean autoRequery) {


        super(context,c,autoRequery);
        level_dao = new Level_Dao(context);


    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.frg_level_lst_view,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        txtLevel = view.findViewById(R.id.frg_level_edittxt_categorir);
        btnDelete = view.findViewById(R.id.frg_level_btn_delete);
        btnUpdate = view.findViewById(R.id.frg_level_btn_update);

        txtLevel.setText(cursor.getString(cursor.getColumnIndexOrThrow(Level_Dao.COLUMN_TITLE)));
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_dao.deleteLevel(cursor.getInt(cursor.getColumnIndexOrThrow(Categorie_Dao.COLUMN_ID)))){
                    Toast.makeText(context,"Categorie is deleted",Toast.LENGTH_LONG);
                    changeCursor(level_dao.SelectAll());
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_dao.updateLevel(cursor.getInt(cursor.getColumnIndexOrThrow(Categorie_Dao.COLUMN_ID)),txtLevel.getText().toString())){
                    Toast.makeText(context,"Categorie is Updated",Toast.LENGTH_LONG).show();
                    changeCursor(level_dao.SelectAll());
                }
            }
        });

    }
}
