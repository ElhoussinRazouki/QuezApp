package Dao.Class;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Dao.Db.Level_Dao;
import Dao.Db.QuestionDao;

public class Level {
    private Integer Id;
    private String Title;


    public Level(Integer id, String title ) {
        this.Id = id;
        this.Title = title;

    }
    public Level(){}
    public ArrayList<Level> listFromCursor(Cursor cursor)
    {
        ArrayList<Level> lst = new ArrayList<>();
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Level level = new Level(cursor.getInt(cursor.getColumnIndexOrThrow(Level_Dao.COLUMN_ID)),cursor.getString(cursor.getColumnIndexOrThrow(Level_Dao.COLUMN_TITLE)));
                Log.i("test",String.valueOf(level.getId())+" "+level.getTitle());
                lst.add(level);
                cursor.moveToNext();
            }
        }
        return lst;
    }

    public Level(String title) {
        Title = title;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


    @Override
    public String toString() {
        return this.Title;
    }
}
