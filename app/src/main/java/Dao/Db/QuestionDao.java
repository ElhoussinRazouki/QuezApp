package Dao.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import Dao.Class.Question;

import Dao.BaseDao;

public class QuestionDao extends BaseDao {
    public static final String TABLE_Name = "question";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ID_CATEGORIE = "idCategorie";
    public static final String COLUMN_ID_LEVEL = "idLevel";
    public static final String COLUMN_QUESTION = "question";
    public static final String COLUMN_REPONSE1 = "reponse1";
    public static final String COLUMN_REPONSE2 = "reponse2";
    public static final String COLUMN_REPONSE3 = "reponse3";
    public static final String COLUMN_REPONSE4 = "reponse4";
    public static final String COLUMN_CORRECT_REPONSE = "correctReponse";
    public static final String CREATE_TABLE = "CREATE TABLE "+TABLE_Name
            +"( "+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_ID_CATEGORIE + " INTEGER REFERENCES "+Categorie_Dao.TABLE_Name+"("+Categorie_Dao.COLUMN_ID+"), "
            + COLUMN_ID_LEVEL + " INTEGER REFERENCES "+Level_Dao.TABLE_Name+"("+Level_Dao.COLUMN_ID+"), "
            + COLUMN_QUESTION + " TEXT, "
            + COLUMN_REPONSE1 + " TEXT, "
            + COLUMN_REPONSE2 + " TEXT, "
            + COLUMN_REPONSE3 + " TEXT, "
            + COLUMN_REPONSE4 + " TEXT, "
            + COLUMN_CORRECT_REPONSE + " TEXT );";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_Name+" ;";
    public QuestionDao(Context context){
        super(context);
    }
    public boolean insert(Question question){
        ContentValues dt = new ContentValues();
        dt.put(COLUMN_ID_CATEGORIE,question.getIdCategorie());
        dt.put(COLUMN_ID_LEVEL,question.getIdLevel());
        dt.put(COLUMN_QUESTION,question.getQuestion());
        dt.put(COLUMN_REPONSE1,question.getReponse1());
        dt.put(COLUMN_REPONSE2,question.getReponse2());
        dt.put(COLUMN_REPONSE3,question.getReponse3());
        dt.put(COLUMN_REPONSE4,question.getReponse4());
        dt.put(COLUMN_CORRECT_REPONSE,question.getCorrectReponse());

        this.db.insert(TABLE_Name,null,dt);

        return true;
    }
    public Cursor SelectAll(){
    return this.db.query(TABLE_Name,null,null,null,null,null,null,null);
    }
    public boolean updateQuestion(int id,int idCategorie,int idLevel,String question , String reponse1, String reponse2, String reponse3, String reponse4 , String correctReponse){
        ContentValues contentValues = new ContentValues();

        Log.i("test","update "+String.valueOf(idLevel));
        contentValues.put(COLUMN_ID_CATEGORIE,idCategorie);
        contentValues.put(COLUMN_ID_LEVEL,idLevel);

        if(question != null){
            contentValues.put(COLUMN_QUESTION,question);
        }
        if(reponse1 != null){
            contentValues.put(COLUMN_REPONSE1,reponse1);
        }
        if(reponse2 != null){
            contentValues.put(COLUMN_REPONSE2,reponse2);
        }
        if(reponse3 != null){
            contentValues.put(COLUMN_REPONSE3,reponse3);
        }
        if(reponse4 != null){
            contentValues.put(COLUMN_REPONSE4,reponse4);
        }
        if(correctReponse != null){
            contentValues.put(COLUMN_CORRECT_REPONSE,correctReponse);
        }
        this.db.update(TABLE_Name,contentValues,"_id = ?",new String[]{String.valueOf(id)});
        return true;
    }
    public boolean deleteQuestion(int id){
        Log.i("test","delet this " +String.valueOf(id));
        String DELETE_QUESTION = "DELETE FROM "+TABLE_Name+" WHERE "+COLUMN_ID+" ="+id;
        this.db.execSQL(DELETE_QUESTION);
        return true;
    }
    public Cursor getQuestion(int idCategory,int idLevel,int nbr){

        String query = "SELECT * FROM "+TABLE_Name
                +" WHERE "+COLUMN_ID_CATEGORIE+"="+idCategory
                +" AND "+COLUMN_ID_LEVEL+"="+idLevel
                +" ORDER BY RANDOM() "
                +" LIMIT "+nbr+" ;";
        Cursor cursor =db.rawQuery(query,null);
        Log.i("test",String.valueOf(cursor.getCount()));
       return cursor ;
    }
    public Cursor getById(int idQuestion){
        String query="SELECT * FROM "+TABLE_Name+" Where "+COLUMN_ID+" = ? ;";
        return db.rawQuery(query,new String[]{String.valueOf(idQuestion)});
    }

}
