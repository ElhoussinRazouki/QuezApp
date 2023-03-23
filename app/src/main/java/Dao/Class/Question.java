package Dao.Class;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import Dao.Db.Categorie_Dao;
import Dao.Db.QuestionDao;

public class Question {
    QuestionDao questionDao;
    private int Id;
    private String question ;
    private String reponse1 ;
    private String reponse2 ;
    private String reponse3 ;
    private String reponse4 ;
    private String correctReponse ;
    private int idCategorie;
    private int idLevel;
    public Question( int idCategorie, int idLevel,String question, String reponse1, String reponse2, String reponse3, String reponse4, String correctReponse) {
        this.idCategorie = idCategorie;
        this.idLevel = idLevel;
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse4 = reponse4;
        this.correctReponse = correctReponse;

    }
    public Question(){}
    public Question(int id, int idCategorie, int idLevel,String question, String reponse1, String reponse2, String reponse3, String reponse4, String correctReponse) {
        this.Id=id;
        this.idCategorie = idCategorie;
        this.idLevel = idLevel;
        this.question = question;
        this.reponse1 = reponse1;
        this.reponse2 = reponse2;
        this.reponse3 = reponse3;
        this.reponse4 = reponse4;
        this.correctReponse = correctReponse;

    }
    public ArrayList<Question> getFromCursor(Cursor cursorSuestion){
        Cursor cursor = cursorSuestion;
        ArrayList<Question> lst = new ArrayList<>();
        if(cursor != null){
           lst = new ArrayList<>();
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){

                Question question = new Question(cursor.getInt(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_ID)),cursor.getInt(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_ID_CATEGORIE)),cursor.getInt(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_ID_LEVEL)),cursor.getString(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_QUESTION)),cursor.getString(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_REPONSE1)),cursor.getString(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_REPONSE2)),cursor.getString(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_REPONSE3)),cursor.getString(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_REPONSE4)),cursor.getString(cursor.getColumnIndexOrThrow(QuestionDao.COLUMN_CORRECT_REPONSE)));
                lst.add(question);
                cursor.moveToNext();
            }
        }
        return lst;
    }
//    public Question getFromId(Cursor cursor){
//
//
//        return question;
//    }

    public String getCorrectReponse() {
        return correctReponse;
    }
    public void setCorrectReponse() {
        this.correctReponse = correctReponse;
    }

    public String getReponse1() {
        return reponse1;
    }
    public void setReponse1(String reponse1) {
        this.reponse1 = reponse1;
    }

    public String getReponse2() {
        return reponse2;
    }
    public void setReponse2(String reponse2) {
        this.reponse2 = reponse2;
    }

    public String getReponse3() {
        return reponse3;
    }
    public void setReponse3(String reponse3) {
        this.reponse3 = reponse3;
    }

    public String getReponse4() {
        return reponse4;
    }
    public void setReponse4(String reponse4) {
        this.reponse4 = reponse4;
    }


    public String getQuestion() {
        return question;
    }
    public void setQuestion(String Question) {
        this.question = Question;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public int getIdLevel() {
        return idLevel;
    }

    public void setIdLevel(int idLevel) {
        this.idLevel = idLevel;
    }
}
