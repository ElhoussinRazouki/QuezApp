package Dao.Class;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Dao.Db.Categorie_Dao;
import Dao.Db.Level_Dao;

public class Categorie {
    private Integer Id;
    private String Title;


    public Categorie(Integer id, String title ) {
        this.Id = id;
        this.Title = title;
    }
    public Categorie(){}
    public ArrayList<Categorie> listFromCursor(Cursor cursor)
    {
        ArrayList<Categorie> lst = new ArrayList<>();
        if(cursor != null){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                Categorie categorie = new Categorie(cursor.getInt(cursor.getColumnIndexOrThrow(Categorie_Dao.COLUMN_ID)),cursor.getString(cursor.getColumnIndexOrThrow(Categorie_Dao.COLUMN_TITLE)));
                lst.add(categorie);
                Log.i("test",categorie.toString());
                cursor.moveToNext();

            }
        }
        return lst;
    }


    public Categorie(String title) {
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
