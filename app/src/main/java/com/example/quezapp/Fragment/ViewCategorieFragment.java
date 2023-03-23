package com.example.quezapp.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.quezapp.CategorieCursorAdapter;
import com.example.quezapp.R;

import Dao.Db.Categorie_Dao;


public class ViewCategorieFragment extends Fragment {

CategorieCursorAdapter categorieCursorAdapter ;
Categorie_Dao categorie_dao ;
ListView listView ;
Cursor categorieCursor ;


    public ViewCategorieFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_categorie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i("test","is created view");
        listView = view.findViewById(R.id.frg_categorie_lst_view);
        Log.i("test","is created listview");
        categorie_dao = new Categorie_Dao(getContext());
        categorieCursor = categorie_dao.SelectAll();
        Log.i("test","is created references");
        categorieCursorAdapter = new CategorieCursorAdapter(getContext(),categorieCursor);
        Log.i("test","is created adapter");
        listView.setAdapter(categorieCursorAdapter);
        Log.i("test","is set to listview");
    }

    @Override
    public void onResume() {
        super.onResume();
        categorieCursorAdapter = new CategorieCursorAdapter(getContext(),categorie_dao.SelectAll());
        listView.setAdapter(categorieCursorAdapter);
    }


}