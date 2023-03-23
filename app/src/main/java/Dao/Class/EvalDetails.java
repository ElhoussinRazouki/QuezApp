package Dao.Class;

import android.database.Cursor;

import java.util.ArrayList;

import Dao.Db.Details_Evaluation_Dao;
import Dao.Db.QuestionDao;

public class EvalDetails {
   private int idEval;
   private int idQuest;
   private String userSelectQuestion;
   private Question Question;

   public EvalDetails(){}
    public EvalDetails(int idEval, int idQuest, String userSelectQuestion) {
        this.idEval = idEval;
        this.idQuest = idQuest;
        this.userSelectQuestion = userSelectQuestion;
    }

    public int getIdEval() {
        return idEval;
    }

    public void setIdEval(int idEval) {
        this.idEval = idEval;
    }

    public int getIdQuest() {
        return idQuest;
    }

    public void setIdQuest(int idQuest) {
        this.idQuest = idQuest;
    }

    public String getUserSelectQuestion() {
        return userSelectQuestion;
    }

    public void setUserSelectQuestion(String userSelectQuestion) {
        this.userSelectQuestion = userSelectQuestion;
    }


    public ArrayList<EvalDetails> getFromCursor(Cursor cursor){
        ArrayList<EvalDetails> ListevalDetails= new ArrayList<>();
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){

                EvalDetails evalDetails = new EvalDetails(cursor.getInt(cursor.getColumnIndexOrThrow(Details_Evaluation_Dao.Column_ID_EVAL)),cursor.getInt(cursor.getColumnIndexOrThrow(Details_Evaluation_Dao.Column_ID_Exam)),cursor.getString(cursor.getColumnIndexOrThrow(Details_Evaluation_Dao.Column_CORRECT_REPONSE)));
                ListevalDetails.add(evalDetails);
                cursor.moveToNext();
            }
        }
       return ListevalDetails;
    }


    public Dao.Class.Question getQuestion() {
        return Question;
    }

    public void setQuestion(Dao.Class.Question question) {
        Question = question;
    }
}
