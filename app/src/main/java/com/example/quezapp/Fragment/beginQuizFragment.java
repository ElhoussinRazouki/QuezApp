package com.example.quezapp.Fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.quezapp.R;
import com.example.quezapp.userActivity;

import java.util.ArrayList;
import java.util.Locale;

import Dao.Class.EvalDetails;
import Dao.Class.Evaluation;
import Dao.Class.Question;
import Dao.Db.Details_Evaluation_Dao;
import Dao.Db.Evaluation_Dao;
import Dao.Db.Level_Dao;
import Dao.Db.QuestionDao;
import Dao.Db.User_Dao;


public class beginQuizFragment extends Fragment implements View.OnClickListener {

    int startPosition;
    int currentPosition;
    int lastPosition;
    protected RadioGroup radioGroup;
    protected RadioButton radioButton1,radioButton2,radioButton3,radioButton4;
    protected TextView QuestionTitle;
    protected Button buttonPrev,buttonNext;
    protected ProgressBar progressBar;
    Bundle based;
    Evaluation_Dao evaluation_dao;
    Evaluation evaluation;
    Details_Evaluation_Dao details_evaluation_dao;
    QuestionDao questionDao;
    ArrayList<Question> listQuestion;
    ArrayList<EvalDetails> literalDetails;
    CountDownTimer countDownTimer;
    Dialog dialog;
    ProgressBar progressBarRing ;
    TextView txt_Score ,txt_time,txtCounterQuestion;
    Button btnDetails_popup;
    userActivity userActivity;
    ImageView indices_time;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        userActivity = (com.example.quezapp.userActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_begin_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get data
        if (getArguments() !=null){
            based = getArguments();
        }
        //set references
        buttonNext = view.findViewById(R.id.btn_next_quiz);
        buttonPrev = view.findViewById(R.id.btn_prev_quiz);
        QuestionTitle = view.findViewById(R.id.txt_quiz_question);
        radioButton1 = view.findViewById(R.id.txt_quiz_rep1);
        radioButton2 = view.findViewById(R.id.txt_quiz_rep2);
        radioButton3 = view.findViewById(R.id.txt_quiz_rep3);
        radioButton4 = view.findViewById(R.id.txt_quiz_rep4);
        radioGroup = view.findViewById(R.id.radioGroup);
        progressBar = view.findViewById(R.id.progressBar);
        txt_time=view.findViewById(R.id.txt_time);
        txtCounterQuestion=view.findViewById(R.id.txtCounterQuestion);
        indices_time = view.findViewById(R.id.indice_time);
        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.pop_up_finich_quiz);
        progressBarRing = dialog.findViewById(R.id.ring_progress);
        txt_Score = dialog.findViewById(R.id.txt_score_popup);
        btnDetails_popup=dialog.findViewById(R.id.btn_details_popUp);

        //configure Progress Bar
        progressBar.setProgress(0);
        progressBar.setMax(100);

        // when user select a response
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.txt_quiz_rep1:
                        literalDetails.get(currentPosition).setUserSelectQuestion(radioButton1.getText().toString());
                        break;
                    case R.id.txt_quiz_rep2:
                        literalDetails.get(currentPosition).setUserSelectQuestion(radioButton2.getText().toString());
                        break;
                    case R.id.txt_quiz_rep3:
                        literalDetails.get(currentPosition).setUserSelectQuestion(radioButton3.getText().toString());
                        break;
                    case R.id.txt_quiz_rep4:
                        literalDetails.get(currentPosition).setUserSelectQuestion(radioButton4.getText().toString());
                        break;
                }
            }
        });

        //button go to next question
        buttonNext.setOnClickListener(beginQuizFragment.this);

        //button go previous Question
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                if(buttonNext.getText()=="Submit")
                {
                    buttonNext.setText("Next");
                    buttonNext.setOnClickListener(beginQuizFragment.this);
                }
                if(!(currentPosition<=startPosition)){
                    radioGroup.clearCheck();
                    currentPosition --;
                    progressBar.setProgress((currentPosition*100)/lastPosition,true);

                }
                refresh();
            }
        });



        evaluation_dao = new Evaluation_Dao(getContext());
        Bundle bundle = getArguments();
        int idEval = evaluation_dao.createForEval(bundle.getInt(User_Dao.Column_Id),bundle.getInt("Categorie_Dao.COLUMN_ID"),bundle.getInt("Level_Dao.COLUMN_ID"));
        evaluation = new Evaluation(idEval,bundle.getInt(User_Dao.Column_Id),bundle.getInt("Categorie_Dao.COLUMN_ID"),bundle.getInt("Level_Dao.COLUMN_ID"),null,null);
        questionDao = new QuestionDao(getContext());
        Log.i("test","user id " +String.valueOf(getArguments().getInt(User_Dao.Column_Id)));
        Log.i("test","categories " +String.valueOf(getArguments().getInt("Categorie_Dao.COLUMN_ID")));
        Log.i("test","level " +String.valueOf(getArguments().getInt("Level_Dao.COLUMN_ID")));
        Log.i("test",Level_Dao.COLUMN_ID);

        Cursor cursor = questionDao.getQuestion(getArguments().getInt("Categorie_Dao.COLUMN_ID"),getArguments().getInt("Level_Dao.COLUMN_ID"),10);

       // Toast.makeText(getContext(), String.valueOf(cursor.getCount()), Toast.LENGTH_SHORT).show();


        listQuestion =new Question().getFromCursor(cursor);
        literalDetails = new ArrayList<>();


        for(int counter =0 ; counter<listQuestion.size();counter++){
            literalDetails.add(new EvalDetails(evaluation.getId(),listQuestion.get(counter).getId(),"null"));
        }

        startPosition = 0;
        lastPosition=cursor.getCount()-1;
        //Toast.makeText(getContext(), "count is "+ String.valueOf(listQuestion.size()), Toast.LENGTH_SHORT).show();
        refresh();

        //make counter for quiz

        countDownTimer = new CountDownTimer(720000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int minutes = (int)(millisUntilFinished/1000)/60;
                int second = (int) (millisUntilFinished/1000)%60;
                if((second%2)==0){
                    indices_time.setVisibility(View.VISIBLE);
                }else {
                    indices_time.setVisibility(View.INVISIBLE);
                }

                String timeLeft = String.format(Locale.getDefault(),"%02d:%02d",minutes,second);
                txt_time.setText(timeLeft);

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onFinish() {
                try {
                    submit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        countDownTimer.start();


    }

    //function for set data to the view based on the current position
    //current position means the position of the question in the Array of questions
    //current position it start in 0
    //current position is changed depending button next or previous
    public void refresh(){


        if(currentPosition>=startPosition && currentPosition<=lastPosition)
            {
                QuestionTitle.setText(listQuestion.get(currentPosition).getQuestion());
                radioButton1.setText(listQuestion.get(currentPosition).getReponse1());
                radioButton2.setText(listQuestion.get(currentPosition).getReponse2());
                radioButton3.setText(listQuestion.get(currentPosition).getReponse3());
                radioButton4.setText(listQuestion.get(currentPosition).getReponse4());
                txtCounterQuestion.setText(String.valueOf(currentPosition+1)+"/"+String.valueOf(lastPosition+1));

            if(literalDetails.get(currentPosition).getUserSelectQuestion() != null)
                {
                    String userSelectQuest  = literalDetails.get(currentPosition).getUserSelectQuestion();
                    if(userSelectQuest.equals(radioButton1.getText().toString())){radioButton1.setChecked(true);}
                    if(userSelectQuest.equals(radioButton2.getText().toString())){radioButton2.setChecked(true);}
                    if(userSelectQuest.equals(radioButton3.getText().toString())){radioButton3.setChecked(true);}
                    if(userSelectQuest.equals(radioButton4.getText().toString())){radioButton4.setChecked(true);}
                }
            }
            if (currentPosition != startPosition && currentPosition != lastPosition)
            {
                buttonPrev.setEnabled(true);
                buttonNext.setEnabled(true);
            }
            else if (currentPosition==startPosition){buttonPrev.setEnabled(false);}
            else if(currentPosition ==lastPosition)
            {
             buttonNext.setText("Submit") ;
             buttonNext.setOnClickListener(new View.OnClickListener() {
                   @RequiresApi(api = Build.VERSION_CODES.N)
                   @Override
                   public void onClick(View v) {
                       try {
                           submit();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
             });
         }
      }


    @RequiresApi(api = Build.VERSION_CODES.N)

     public void  submit() throws InterruptedException {
        int userScour =0;
        int scoreFinal = 0;
        for(int counter= 0 ;counter <lastPosition+1 ;counter++){
            if(literalDetails.get(counter).getUserSelectQuestion().equals(listQuestion.get(counter).getCorrectReponse())){
                userScour ++;
            }
        }
        if (userScour>0) {
            progressBarRing.setProgress(0);
            scoreFinal = (userScour * 100) / (lastPosition + 1);
            evaluation.setScore(scoreFinal);
            dialog.getWindow();
            dialog.show();


            int finalScoreFinal = scoreFinal;
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    for(int count = 0 ;count<=finalScoreFinal;count++){
                        progressBarRing.setProgress(count);
                        int finalCount = count;
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txt_Score.setText(String.valueOf(finalCount)+" %");
                            }
                        });


                        try {
                            Thread.sleep(Math.round((finalScoreFinal *20)/100));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();



        }
        else {
            evaluation.setScore(0);
            dialog.getWindow();
            dialog.show();
            progressBarRing.setMax(100);
            progressBarRing.setProgress(10, true);
            txt_Score.setText(" nothing :(");
        }

        evaluation.setScore(scoreFinal);
        evaluation_dao.update(evaluation);

        details_evaluation_dao = new Details_Evaluation_Dao(getContext());
        details_evaluation_dao.insertList(literalDetails);

        btnDetails_popup.setOnClickListener(view ->{
            this.userActivity.changeFragment(new UserHistoryFragment());
            dialog.dismiss();
        });




    }





    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View v) {
        if(!(currentPosition>=lastPosition)){
            radioGroup.clearCheck();
            currentPosition ++;
            progressBar.setProgress((currentPosition*100)/lastPosition,true);
        }
        refresh();
    }


}