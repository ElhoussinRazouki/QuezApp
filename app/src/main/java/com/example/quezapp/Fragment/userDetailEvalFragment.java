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

import com.example.quezapp.R;
import com.example.quezapp.UserEvalDetailAdapter;

import java.util.ArrayList;

import Dao.Class.EvalDetails;
import Dao.Class.Question;
import Dao.Db.Details_Evaluation_Dao;
import Dao.Db.QuestionDao;

public class userDetailEvalFragment extends Fragment {
    ListView listView;
    Bundle bundle;
    Details_Evaluation_Dao details_evaluation_dao;
    QuestionDao questionDao;
    EvalDetails evalDetails;
    Question question;


    public userDetailEvalFragment() {

        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            bundle = getArguments();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail_eval, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        evalDetails = new EvalDetails();
        question = new Question();
        listView = view.findViewById(R.id.userEvalDetailsListView);
        details_evaluation_dao = new Details_Evaluation_Dao(getContext());
        questionDao = new QuestionDao(getContext());
        Cursor cursorEva = details_evaluation_dao.getEvalDetails(bundle.getInt(Details_Evaluation_Dao.Column_ID_EVAL));
        ArrayList<EvalDetails> listevalD = evalDetails.getFromCursor(cursorEva);
        ArrayList<Question> listQuestion = question.getFromCursor(cursorEva);
        UserEvalDetailAdapter userEvalDetailAdapter;
        for (int counter1 = 0 ;counter1<cursorEva.getCount();counter1++){

            for (int counter2 = 0 ;counter2<cursorEva.getCount();counter2++){
                if(listevalD.get(counter1).getIdQuest()==listQuestion.get(counter2).getId()){
                    listevalD.get(counter1).setQuestion(listQuestion.get(counter2));
                }

            }
        }
        userEvalDetailAdapter = new UserEvalDetailAdapter(getContext(),listevalD);
        listView.setAdapter(userEvalDetailAdapter);



    }
}