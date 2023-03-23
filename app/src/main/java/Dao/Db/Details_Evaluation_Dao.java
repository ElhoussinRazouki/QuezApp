package Dao.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;


import java.util.ArrayList;

import Dao.BaseDao;
import Dao.Class.EvalDetails;
import Dao.Db.Evaluation_Dao;

public class Details_Evaluation_Dao extends BaseDao {

    public static final String Table_Name = "Details";
    public static final String Column_ID_EVAL = "idEvaluation";
    public static final String Column_ID_Exam = "idQuestion";
    public static final String Column_CORRECT_REPONSE = "correctReponse";
    public static final String Create_Table = "CREATE TABLE " +Table_Name+
            "("+Column_ID_EVAL+" INTEGER REFERENCES "+ Evaluation_Dao.Table_Name+"("+Evaluation_Dao.Column_Id+"),"+
            Column_ID_Exam+" INTEGER REFERENCES "+ QuestionDao.TABLE_Name+"("+QuestionDao.COLUMN_ID+"),"+
            Column_CORRECT_REPONSE+" TEXT );";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+Table_Name+" ;";
    public Details_Evaluation_Dao(Context context) {
        super(context);
    }

    public void insertList(ArrayList<EvalDetails> listevalDetails) {

        for(int i=0 ; i<listevalDetails.size();i++){
            ContentValues contentValues = new ContentValues();
            contentValues.put(Column_ID_EVAL,listevalDetails.get(i).getIdEval());
            contentValues.put(Column_ID_Exam,listevalDetails.get(i).getIdQuest());
            contentValues.put(Column_CORRECT_REPONSE,listevalDetails.get(i).getUserSelectQuestion());

            db.insert(Table_Name,null,contentValues);
        }
    }
    public Cursor getEvalDetails(int idEval){
        String query = "SELECT * FROM "+Table_Name+" INNER JOIN "+QuestionDao.TABLE_Name+" ON "+QuestionDao.TABLE_Name+"."+QuestionDao.COLUMN_ID+" = "+Table_Name+"."+Column_ID_Exam+" WHERE "+Column_ID_EVAL+"= ? ;";
        return db.rawQuery(query,new String[]{String.valueOf(idEval)});
    }

}
