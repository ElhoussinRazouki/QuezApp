package Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import Dao.Db.Categorie_Dao;
import Dao.Db.Details_Evaluation_Dao;
import Dao.Db.Evaluation_Dao;
import Dao.Db.Level_Dao;
import Dao.Db.QuestionDao;
import Dao.Db.User_Dao;

public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public DatabaseOpenHelper( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(Categorie_Dao.CREATE_TABLE);
        sqLiteDatabase.execSQL(Level_Dao.CREATE_TABLE);
        sqLiteDatabase.execSQL(QuestionDao.CREATE_TABLE);
        sqLiteDatabase.execSQL(User_Dao.CREATE_TABLE);
        sqLiteDatabase.execSQL(Evaluation_Dao.Create_Table);
        sqLiteDatabase.execSQL(Details_Evaluation_Dao.Create_Table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(Details_Evaluation_Dao.DROP_TABLE);
        sqLiteDatabase.execSQL(Evaluation_Dao.DROP_TABLE);
        sqLiteDatabase.execSQL(User_Dao.DROP_TABLE);
        sqLiteDatabase.execSQL(QuestionDao.DROP_TABLE);
        sqLiteDatabase.execSQL(Level_Dao.DROP_TABLE);
        sqLiteDatabase.execSQL(Categorie_Dao.DROP_TABLE);
        onCreate(sqLiteDatabase);

    }
}
