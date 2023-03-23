package com.example.quezapp;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import Dao.Class.Categorie;
import Dao.Db.Categorie_Dao;

public class CategorieCursorAdapter extends BaseAdapter {

    EditText txtCategorie;
    Button btnUpdate , btnDelete;
    Categorie_Dao categorie_dao ;
    Context maincontext;
    ArrayList<Categorie> listCategorie;

    public CategorieCursorAdapter(Context context, Cursor c)
    {
        categorie_dao = new Categorie_Dao(context);
        listCategorie = new Categorie().listFromCursor(c);
        maincontext=context;

    }


    @Override
    public int getCount() {
        return listCategorie.size();
    }

    @Override
    public Object getItem(int position) {
        return listCategorie.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listCategorie.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = LayoutInflater.from(maincontext).inflate(R.layout.frg_category_lst_view,parent,false);

        txtCategorie = rowView.findViewById(R.id.frg_categorie_edittxt_categorir);
        btnDelete = rowView.findViewById(R.id.frg_categorie_btn_delete);
        btnUpdate = rowView.findViewById(R.id.frg_categorie_btn_update);

        Categorie categorie = listCategorie.get(position);

        txtCategorie.setText(categorie.getTitle());
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categorie_dao.deleteCategorie(categorie.getId())){

                    listCategorie = categorie.listFromCursor(categorie_dao.SelectAll());
                    notifyDataSetChanged();
                    Toast.makeText(maincontext,"Categorie is deleted",Toast.LENGTH_SHORT);
                }
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categorie_dao.updateCategorie(categorie.getId(),txtCategorie.getText().toString())){
                    Log.i("test",String.valueOf(categorie.getId())+txtCategorie.getText().toString());
                    listCategorie = categorie.listFromCursor(categorie_dao.SelectAll());
                    notifyDataSetChanged();
                    Toast.makeText(maincontext,"Categorie is Updated",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rowView;
    }
}
