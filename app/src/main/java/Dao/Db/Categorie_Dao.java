package Dao.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import Dao.Class.Categorie;
import Dao.Class.Question;

public class Categorie_Dao extends Dao.BaseDao {


    public static final String TABLE_Name = "Categorie";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String CREATE_TABLE = "CREATE TABLE " +TABLE_Name+
            "("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE+" TEXT NOT NULL);";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_Name+" ;";

    public Categorie_Dao(Context context) {
        super(context);
    }

    public boolean insert(Categorie categorie){
        ContentValues dt = new ContentValues();
        dt.put(COLUMN_TITLE,categorie.getTitle());
        this.db.insert(TABLE_Name,null,dt);

        return true;
    }
    public Cursor SelectAll(){
        return db.query(TABLE_Name,null,null,null,null,null,null);
    }
    public boolean updateCategorie(int id,String title ){
        ContentValues contentValues = new ContentValues();
        if(title != null){
            contentValues.put(COLUMN_TITLE,title);
        }
        this.db.update(TABLE_Name,contentValues,"_id = ?",new String[]{String.valueOf(id)});
        return true;
    }
    public boolean deleteCategorie(int id){
        String DELETE_CATEGORIE = "DELETE FROM "+TABLE_Name+" WHERE "+COLUMN_ID+" ="+id;
        this.db.execSQL(DELETE_CATEGORIE);
        return true;
    }


}
