package com.example.quezapp;

import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import Dao.Db.QuestionDao;


public class AffichageFragment extends Fragment {
    ListView lstView ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(getContext(),"fragmen afichage ",Toast.LENGTH_LONG).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_affichage, null, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lstView = view.findViewById(R.id.lstView);
        QuestionDao questionDao = new QuestionDao(getContext());
        Cursor questionCursor = questionDao.SelectAll();
        QuestionCursorAdapter adapter = new QuestionCursorAdapter(getContext(),questionCursor);
        lstView.setAdapter(adapter);


    }
}