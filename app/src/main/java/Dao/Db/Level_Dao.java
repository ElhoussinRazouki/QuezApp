package Dao.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import Dao.BaseDao;
import Dao.Class.Categorie;
import Dao.Class.Level;

public class Level_Dao extends BaseDao {

    public static final String TABLE_Name = "Level";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String CREATE_TABLE = "CREATE TABLE " +TABLE_Name+
            "("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_TITLE+" TEXT NOT NULL);";

    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+TABLE_Name+" ;";

    public Level_Dao(Context context) {
        super(context);
    }



    public boolean insert(Level level){
        ContentValues dt = new ContentValues();
        dt.put(COLUMN_TITLE,level.getTitle());
        this.db.insert(TABLE_Name,null,dt);

        return true;
    }
    public Cursor SelectAll(){
        return this.db.query(TABLE_Name,null,null,null,null,null,null,null);
    }
    public boolean updateLevel(int id,String title ){
        ContentValues contentValues = new ContentValues();
        if(title != null){
            contentValues.put(COLUMN_TITLE,title);
        }
        this.db.update(TABLE_Name,contentValues,"_id = ?",new String[]{String.valueOf(id)});
        return true;
    }
    public boolean deleteLevel(int id){
        String DELETE_LEVEL = "DELETE FROM "+TABLE_Name+" WHERE "+COLUMN_ID+" ="+id;
        this.db.execSQL(DELETE_LEVEL);
        return true;
    }

}
