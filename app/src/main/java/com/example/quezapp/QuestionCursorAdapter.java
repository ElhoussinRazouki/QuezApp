package com.example.quezapp;

import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cursoradapter.widget.CursorAdapter;

import java.util.ArrayList;

import Dao.Class.Question;
import Dao.Db.QuestionDao;

public class QuestionCursorAdapter extends BaseAdapter {

    TextView  txt_question ,txt_correct_reponse ;
    String  txt_reponse1, txt_reponse2, txt_reponse3, txt_reponse4 ;
    int txt_id ;
    FragmentManager fragmentManager ;
    QuestionDao questionDao ;
    MainActivity activityMain ;
    ArrayList<Question> listQuestion;


    public QuestionCursorAdapter(Context context, Cursor c) {

        questionDao = new QuestionDao(context);
        activityMain =(MainActivity) context;
        listQuestion = new ArrayList<>();
        Question question= new Question();
        listQuestion=question.getFromCursor(c);

    }


    @Override
    public int getCount() {
        return listQuestion.size();
    }

    @Override
    public Object getItem(int position) {
        return listQuestion.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listQuestion.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //inflate view
        View viewRow = LayoutInflater.from(activityMain).inflate(R.layout.lst_qs,null);

        Question question = listQuestion.get(position);
        txt_question = viewRow.findViewById(R.id.txtQuestion);
        txt_correct_reponse = viewRow.findViewById(R.id.txtReponse);
        Button btnEdit = viewRow.findViewById(R.id.btnEdit);
        Button btnDelet = viewRow.findViewById(R.id.btnDelet);
        txt_id =question.getId();
        Log.i("test",String.valueOf(question.getId()));
        txt_reponse1 = question.getReponse1();
        txt_reponse2 = question.getReponse2();
        txt_reponse3 = question.getReponse3();
        txt_reponse4 = question.getReponse4();
        txt_question.setText(question.getQuestion());
        txt_correct_reponse.setText(question.getCorrectReponse());

        btnDelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(questionDao.deleteQuestion(question.getId())){
                    Toast.makeText(view.getContext(),txt_question.getText().toString()+"  is deleted",Toast.LENGTH_LONG);
                    listQuestion = question.getFromCursor(questionDao.SelectAll());
                    notifyDataSetChanged();

                }

            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {

                SaisieFragment saisieFragment = new SaisieFragment();
                Bundle bundle = new Bundle();


                bundle.putInt(QuestionDao.COLUMN_ID,question.getId());
                bundle.putInt(QuestionDao.COLUMN_ID_CATEGORIE,question.getIdCategorie());
                bundle.putInt(QuestionDao.COLUMN_ID_LEVEL,question.getIdLevel());
                bundle.putString(QuestionDao.COLUMN_QUESTION,question.getQuestion());
                bundle.putString(QuestionDao.COLUMN_REPONSE1,question.getReponse1());
                bundle.putString(QuestionDao.COLUMN_REPONSE2,question.getReponse2());
                bundle.putString(QuestionDao.COLUMN_REPONSE3,question.getReponse3());
                bundle.putString(QuestionDao.COLUMN_REPONSE4,question.getReponse4());
                bundle.putString(QuestionDao.COLUMN_CORRECT_REPONSE,question.getCorrectReponse());
                saisieFragment.setArguments(bundle);
                activityMain.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity,saisieFragment).commit();
                // new MainActivity().getFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity,saisieFragment).commit();

            }
        });



        return viewRow;
    }






}
