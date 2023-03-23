package com.example.quezapp.Fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.quezapp.AdapterSpinner;
import com.example.quezapp.CategorieAdapterSpinner;
import com.example.quezapp.LevelAdapterSpinner;
import com.example.quezapp.R;
import com.example.quezapp.userActivity;

import java.util.ArrayList;

import Dao.Class.Categorie;
import Dao.Class.Level;
import Dao.Db.Categorie_Dao;
import Dao.Db.Level_Dao;
import Dao.Db.User_Dao;


public class StartQuizFragment extends Fragment {
    Spinner spinnerCategorie,spinnerLevel;
    Level_Dao level_dao;
    Categorie_Dao categorie_dao;
    int userId;
    Button btnStartQuiz;
    userActivity mainContext;
    int idCategorie;
    int idLevel ;
    SurClickListener listener;


    public StartQuizFragment(Context context) {
        // Required empty public constructor
        mainContext = (userActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start_quiz, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        listener = (SurClickListener) context;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnStartQuiz = view.findViewById(R.id.btn_start_quiz);
        spinnerCategorie = view.findViewById(R.id.spinner_start_quiz_categorie);
        spinnerLevel = view.findViewById(R.id.spinner_start_quiz_level);
        categorie_dao = new Categorie_Dao(getContext());
        level_dao = new Level_Dao(getContext());
        userId = getArguments().getInt(User_Dao.Column_Id);

        Cursor cursorCategori = categorie_dao.SelectAll();
        CategorieAdapterSpinner adapterSpinnerCateorie = new CategorieAdapterSpinner(getContext(),new Categorie().listFromCursor(cursorCategori));
        spinnerCategorie.setAdapter(adapterSpinnerCateorie);


        Cursor cursorLevel = level_dao.SelectAll();

        LevelAdapterSpinner adapterSpinnerLevel = new LevelAdapterSpinner(getContext(),new Level().listFromCursor(cursorLevel));
        spinnerLevel.setAdapter(adapterSpinnerLevel);


        btnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bnd = new Bundle();
//                Log.i("test",Level_Dao.COLUMN_ID);
                bnd.putInt("Categorie_Dao.COLUMN_ID", (int) spinnerCategorie.getSelectedItemId());
                bnd.putInt("Level_Dao.COLUMN_ID", (int) spinnerLevel.getSelectedItemId());
                bnd.putInt(User_Dao.Column_Id,userId);
                listener.surClick(v, bnd);
            }
        });

    }
    public interface SurClickListener{
        void surClick(View view,Bundle Data);
    }
}