package Dao.Class;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import Dao.Db.Categorie_Dao;
import Dao.Db.Evaluation_Dao;
import Dao.Db.Level_Dao;
import Dao.Db.QuestionDao;

public class EvalClass {
    private int idEval;
    private String Categorie,level,date,score;

    public EvalClass(int idEval, String categorie, String level, String date,String score) {
        this.idEval = idEval;
        this.Categorie = categorie;
        this.level = level;
        this.date = date;
        this.score=score;
    }
    public EvalClass() {

    }
    public ArrayList<EvalClass> getFromCursor(Cursor cursorEval){
        Cursor cursor = cursorEval;
        Log.i("test","query is completed");
        ArrayList<EvalClass> lst = new ArrayList<>();
        if(cursor != null){
            lst = new ArrayList<>();
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                EvalClass Eval = new EvalClass(cursor.getInt(cursor.getColumnIndexOrThrow(Evaluation_Dao.Column_Id)),cursor.getString(cursor.getColumnIndexOrThrow("categorieTitle")),cursor.getString(cursor.getColumnIndexOrThrow("levelTitle")),cursor.getString(cursor.getColumnIndexOrThrow(Evaluation_Dao.Column_Date)),cursor.getString(cursor.getColumnIndexOrThrow(Evaluation_Dao.Column_Score)));
                lst.add(Eval);
                cursor.moveToNext();
            }
        }
        return lst;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCategorie() {
        return Categorie;
    }

    public void setCategorie(String categorie) {
        Categorie = categorie;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdEval() {
        return idEval;
    }

    public void setIdEval(int idEval) {
        this.idEval = idEval;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
