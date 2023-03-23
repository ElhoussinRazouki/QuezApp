package Dao.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

import Dao.BaseDao;
import Dao.Class.Evaluation;


public class Evaluation_Dao extends BaseDao {

    public static final String Table_Name = "Exam";
    public static final String Column_Id = "_id";
    public static final String Column_Id_User = "IdUser";
    public static final String Column_Id_Category = "IdCategory";
    public static final String Column_id_level="IdLevel";
    public static final String Column_Date = "DateExam";
    public static final String Column_Score = "Score";
    public static final String Create_Table = "CREATE TABLE " +Table_Name+
            "("+Column_Id+" INTEGER PRIMARY KEY AUTOINCREMENT," +
            Column_Id_Category+" INTEGER REFERENCES "+ Categorie_Dao.TABLE_Name+"("+Categorie_Dao.COLUMN_ID+") ,"+
            Column_id_level+" INTEGER REFERENCES "+ Level_Dao.TABLE_Name+"("+Level_Dao.COLUMN_ID+") ,"+
            Column_Id_User+" INTEGER REFERENCES "+ User_Dao.Table_Name+"("+User_Dao.Column_Id+") ,"+
            Column_Date+" DATE ," +
            Column_Score+" INTEGER );" ;
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+Table_Name+" ;";
    public Evaluation_Dao(Context context) {
        super(context);
    }

    public int createForEval(int idUser,int idCategorie,int idLevel){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date today= new Date();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_Id_User,idUser);
        contentValues.put(Column_Id_Category,idCategorie);
        contentValues.put(Column_id_level,idLevel);
        contentValues.put(Column_Date,simpleDateFormat.format(today));
        int idInsert = (int) db.insert(Table_Name,null,contentValues);
        return idInsert;

    }

    public void update(Evaluation evaluation) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Column_Score,evaluation.getScore());
        db.update(Table_Name,contentValues,"_id = ?",new String[]{String.valueOf(evaluation.getId())});
    }
    public Cursor getEvaluations(int idUser){
        String query= "SELECT Exam._id, "+Categorie_Dao.TABLE_Name+"."+Categorie_Dao.COLUMN_TITLE+" AS levelTitle , "+Level_Dao.TABLE_Name+"."+Level_Dao.COLUMN_TITLE+" AS categorieTitle ,"+Column_Date+", "+Column_Score+" FROM "+Table_Name+" INNER JOIN "+Categorie_Dao.TABLE_Name+" ON Exam.IdCategory = Categorie._id INNER JOIN LEVEL ON Exam.IdLevel = Level._id WHERE "+Column_Id_User+" = "+idUser+" ;";
        Log.i("test","query is created");
       return db.rawQuery(query,null);
    }
}
