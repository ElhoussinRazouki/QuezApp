package Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class BaseDao {
    protected static final String DATABASE_NAME = "quiz.db";
    protected static final int DATABASE_VERSION = 5;
    protected DatabaseOpenHelper DbHelper ;
    protected SQLiteDatabase db;

    public BaseDao(Context context){
        DbHelper = new DatabaseOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION);
        db = DbHelper.getWritableDatabase();
    }
    public SQLiteDatabase getDataBase(){
        return this.db;
    }
}
