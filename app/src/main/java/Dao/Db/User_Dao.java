package Dao.Db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;



import Dao.Class.User;

public class User_Dao extends Dao.BaseDao {


    public static final String Table_Name = "userTable";
    public static final String Column_Id = "_id";
    public static final String Column_USERNAME = "Username";
    public static final String Column_USERPRENAME = "UserPrename";
    public static final String Column_Email = "Email";
    public static final String Column_Type = "Type";
    public static final String Column_Password = "Password";
    public static final String CREATE_TABLE = "CREATE TABLE "+Table_Name
            +"( "+Column_Id+" INTEGER PRIMARY KEY AUTOINCREMENT , "
            + Column_USERNAME + " TEXT, "
            + Column_USERPRENAME + " TEXT, "
            + Column_Email + " TEXT, "
            + Column_Type + " TEXT, "
            + Column_Password + " TEXT );";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS "+Table_Name+" ;";

    public User_Dao(Context context) {
        super(context);
    }

    public boolean Insert(User user) {

        ContentValues values = new ContentValues();
        values.put(Column_USERNAME, user.getUsername());
        values.put(Column_USERPRENAME, user.getUserPrename());
        values.put(Column_Type, user.getType());
        values.put(Column_Email, user.getEmail());
        values.put(Column_Password, user.getPassword());
        this.db.insert(Table_Name, null, values);
        return true;

    }

    public Cursor login_User(String email, String password) {
        Cursor cursor = this.db.rawQuery("SELECT * FROM " + Table_Name + " WHERE " + Column_Email + "='" + email + "' AND " + Column_Password + " = '" + password + "'", null);
        return cursor;
    }
    public User getUser(int id){
        Cursor cursor = this.db.rawQuery("SELECT * FROM " + Table_Name + " WHERE " + Column_Id + "= ?", new String[]{String.valueOf(id)});
        cursor.moveToFirst();
        User user = new User(cursor.getString(cursor.getColumnIndexOrThrow(Column_USERNAME)),cursor.getString(cursor.getColumnIndexOrThrow(Column_USERPRENAME)),cursor.getString(cursor.getColumnIndexOrThrow(Column_Type)),cursor.getString(cursor.getColumnIndexOrThrow(Column_Email)),cursor.getString(cursor.getColumnIndexOrThrow(Column_Password)));
        cursor.close();
        return user;
    }


}

