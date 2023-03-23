package com.example.quezapp;

import static com.example.quezapp.R.color.*;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;

import Dao.Class.EvalDetails;

public class UserEvalDetailAdapter extends BaseAdapter {
    TextView txtQuestion;
    TextView txtRep1,txtRep2,txtRep3,txtRep4;
    userActivity userActivity;
    ArrayList<EvalDetails> listEvaluation;

    public UserEvalDetailAdapter(Context context, ArrayList<EvalDetails> listEva)
    {
        listEvaluation = listEva;
        userActivity = (com.example.quezapp.userActivity) context;
    }
    @Override
    public int getCount() {
        return listEvaluation.size();
    }

    @Override
    public Object getItem(int position) {
        return listEvaluation.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listEvaluation.get(position).getIdEval();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(userActivity).inflate(R.layout.user_eval_details_lst,null);
        txtQuestion = view.findViewById(R.id.txtUserEvalDetailsQuestion);
        txtRep1 = view.findViewById(R.id.txtUserEvalDetailsRep1);
        txtRep2 = view.findViewById(R.id.txtUserEvalDetailsRep2);
        txtRep3 = view.findViewById(R.id.txtUserEvalDetailsRep3);
        txtRep4 = view.findViewById(R.id.txtUserEvalDetailsRep4);

        txtQuestion.setText(listEvaluation.get(position).getQuestion().getQuestion());
        txtRep1.setText(listEvaluation.get(position).getQuestion().getReponse1());
        txtRep2.setText(listEvaluation.get(position).getQuestion().getReponse2());
        txtRep3.setText(listEvaluation.get(position).getQuestion().getReponse3());
        txtRep4.setText(listEvaluation.get(position).getQuestion().getReponse4());

        if(listEvaluation.get(position).getQuestion().getReponse1().equals(listEvaluation.get(position).getUserSelectQuestion())){
            if(listEvaluation.get(position).getQuestion().getReponse1().equals(listEvaluation.get(position).getQuestion().getCorrectReponse())) {
                txtRep1.setBackgroundResource(purple_500);
            }
            else {
                txtRep1.setBackgroundResource(android.R.color.holo_red_dark);

            }
        }
        if(listEvaluation.get(position).getQuestion().getReponse2().equals(listEvaluation.get(position).getUserSelectQuestion())){
            if(listEvaluation.get(position).getQuestion().getReponse2().equals(listEvaluation.get(position).getQuestion().getCorrectReponse())) {
                txtRep2.setBackgroundResource(purple_500);
            }
            else {
                txtRep2.setBackgroundResource(android.R.color.holo_red_dark);

            }
        }
        if(listEvaluation.get(position).getQuestion().getReponse3().equals(listEvaluation.get(position).getUserSelectQuestion())){
            if(listEvaluation.get(position).getQuestion().getReponse3().equals(listEvaluation.get(position).getQuestion().getCorrectReponse())) {
                txtRep3.setBackgroundResource(purple_500);
            }
            else {
                txtRep3.setBackgroundResource(android.R.color.holo_red_dark);

            };
        }
        if(listEvaluation.get(position).getQuestion().getReponse4().equals(listEvaluation.get(position).getUserSelectQuestion())){
            if(listEvaluation.get(position).getQuestion().getReponse4().equals(listEvaluation.get(position).getQuestion().getCorrectReponse())) {
                txtRep4.setBackgroundResource(purple_500);
            }
            else {
                txtRep4.setBackgroundResource(android.R.color.holo_red_dark);

            }
        }



        return view;
    }
}