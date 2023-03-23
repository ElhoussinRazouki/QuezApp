package com.example.quezapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Dao.Class.Categorie;
import Dao.Class.Level;
import Dao.Class.Question;
import Dao.Db.Categorie_Dao;
import Dao.Db.Level_Dao;
import Dao.Db.QuestionDao;

public class SaisieFragment extends Fragment implements View.OnClickListener {
    protected Button btnAdd;
    protected EditText question;
    protected EditText reponse1;
    protected EditText reponse2;
    protected EditText reponse3;
    protected EditText reponse4;
    protected Spinner correctReponse;
    protected Spinner spinnerCategorie;
    protected Spinner spinnerLevel;
    protected QuestionDao questionDao;
    protected Categorie_Dao categorie_dao;
    protected Level_Dao level_dao;
    protected Bundle dtFromFragAffich ;
    protected String selectedCorrectresponse ;
    protected boolean forUpdate;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        dtFromFragAffich = this.getArguments();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saisie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        questionDao = new QuestionDao(getContext());
        categorie_dao = new Categorie_Dao(getContext());
        level_dao = new Level_Dao(getContext());

        //definde object
        question = getView().findViewById(R.id.question);
        reponse1 = getView().findViewById(R.id.reponse1);
        reponse2 = getView().findViewById(R.id.reponse2);
        reponse3 = getView().findViewById(R.id.reponse3);
        reponse4 = getView().findViewById(R.id.reponse4);
        btnAdd = getView().findViewById(R.id.btnAdd);
        correctReponse = getView().findViewById(R.id.correctReponse);
        spinnerCategorie = getView().findViewById(R.id.saisie_question_spinner_categorie);
        spinnerLevel = getView().findViewById(R.id.saisie_question_spinner_level);

        //fill spinners
        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(this.getContext(),R.array.SpinnerCorrectReponse, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        correctReponse.setAdapter(adapterSpinner);

        ArrayList<Categorie> listCategorie = new Categorie().listFromCursor(categorie_dao.SelectAll());
       // ArrayAdapter<Categorie> adapterCategorie = new ArrayAdapter<Categorie>(getContext(), android.R.layout.simple_spinner_dropdown_item,listCategorie);

        CategorieAdapterSpinner adapterCategorie = new CategorieAdapterSpinner(getContext(),listCategorie);
        spinnerCategorie.setAdapter(adapterCategorie);

        ArrayList<Level> listLevel = new Level().listFromCursor(level_dao.SelectAll());
       // ArrayAdapter<Level> adapterLevel = new ArrayAdapter<Level>(getContext(), android.R.layout.simple_spinner_dropdown_item,listLevel);
        LevelAdapterSpinner adapterLevel = new LevelAdapterSpinner(getContext(),listLevel);
        spinnerLevel.setAdapter(adapterLevel);

        //get value of reponse in selected Item of spinner
        correctReponse.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (correctReponse.getSelectedItem().toString())
                {
                    case "reponse1":
                        selectedCorrectresponse = reponse1.getText().toString();
                        break;
                    case "reponse2":
                        selectedCorrectresponse = reponse2.getText().toString();
                        break;
                    case "reponse3":
                        selectedCorrectresponse = reponse3.getText().toString();
                        break;
                    case "reponse4":
                        selectedCorrectresponse = reponse4.getText().toString();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


        //if the fragment created to edit a question
        if(getArguments()!= null){

            //change text of button from submit add to update
            btnAdd.setText("Update");
            //affect the valuse come from argument to the object in this fragment
            question.setText(dtFromFragAffich.getString(QuestionDao.COLUMN_QUESTION));
            reponse1.setText(dtFromFragAffich.getString(QuestionDao.COLUMN_REPONSE1));
            reponse2.setText(dtFromFragAffich.getString(QuestionDao.COLUMN_REPONSE2));
            reponse3.setText(dtFromFragAffich.getString(QuestionDao.COLUMN_REPONSE3));
            reponse4.setText(dtFromFragAffich.getString(QuestionDao.COLUMN_REPONSE4));
            int position=adapterLevel.getPosition(dtFromFragAffich.getInt(Level_Dao.COLUMN_ID));
            Log.i("test",String.valueOf(position));
            spinnerLevel.setSelection(position);
            spinnerCategorie.setSelection(adapterCategorie.getPosition(dtFromFragAffich.getInt(Categorie_Dao.COLUMN_ID)));
            if(dtFromFragAffich.getString(QuestionDao.COLUMN_CORRECT_REPONSE).equals(reponse1.getText().toString())){correctReponse.setSelection(0);}
            if(dtFromFragAffich.getString(QuestionDao.COLUMN_CORRECT_REPONSE).equals(reponse2.getText().toString())){correctReponse.setSelection(1);}
            if(dtFromFragAffich.getString(QuestionDao.COLUMN_CORRECT_REPONSE).equals(reponse3.getText().toString())){correctReponse.setSelection(2);}
            if(dtFromFragAffich.getString(QuestionDao.COLUMN_CORRECT_REPONSE).equals(reponse4.getText().toString())){correctReponse.setSelection(3);}
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    if(questionDao.updateQuestion(dtFromFragAffich.getInt(QuestionDao.COLUMN_ID), Math.toIntExact(spinnerCategorie.getSelectedItemId()), Math.toIntExact(spinnerLevel.getSelectedItemId()),question.getText().toString(),reponse1.getText().toString(),reponse2.getText().toString(),reponse3.getText().toString(),reponse4.getText().toString(),selectedCorrectresponse)){
                        Toast.makeText(getContext(), "question is updated", Toast.LENGTH_SHORT).show();
                        Log.i("test","spinner category "+String.valueOf(spinnerCategorie.getSelectedItemId()));
                        Log.i("test","spinner Level "+String.valueOf(spinnerLevel.getSelectedItemId()));
                        btnAdd.setEnabled(false);
                        question.setText("");
                        reponse1.setText("");
                        reponse2.setText("");
                        reponse3.setText("");
                        reponse4.setText("");
                        setArguments(null);
                    }

                }
            });

        }
        // in the normale ( fragment for saisie question)
        else
        {
            btnAdd.setText("Add");
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Question qs = new Question(listCategorie.get(spinnerCategorie.getSelectedItemPosition()).getId(),listLevel.get(spinnerLevel.getSelectedItemPosition()).getId(),question.getText().toString(),reponse1.getText().toString(),reponse2.getText().toString(),reponse3.getText().toString(),reponse4.getText().toString(),selectedCorrectresponse);
                    if(questionDao.insert(qs)){
                        Toast.makeText(getContext(),"the question is added",Toast.LENGTH_SHORT).show();
                    }
                    question.setText("");
                    reponse1.setText("");
                    reponse2.setText("");
                    reponse3.setText("");
                    reponse4.setText("");
                }
            });
        }

    }

    @Override
    public void onClick(View view) {

    }
}