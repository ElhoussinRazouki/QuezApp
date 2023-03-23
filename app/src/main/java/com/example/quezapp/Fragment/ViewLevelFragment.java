package com.example.quezapp.Fragment;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.quezapp.CategorieCursorAdapter;
import com.example.quezapp.LevelCursorAdapter;
import com.example.quezapp.R;

import Dao.Db.Categorie_Dao;
import Dao.Db.Level_Dao;


public class ViewLevelFragment extends Fragment {

    LevelCursorAdapter levelCursorAdapter ;
    Level_Dao level_dao ;
    ListView listView ;


    public ViewLevelFragment() {
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
        return inflater.inflate(R.layout.fragment_view_level, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.frg_level_lst_view);
        level_dao = new Level_Dao(getContext());
        Cursor cursor = level_dao.SelectAll();
        levelCursorAdapter = new LevelCursorAdapter(getContext(),cursor,false);
        listView.setAdapter(levelCursorAdapter);
    }
}