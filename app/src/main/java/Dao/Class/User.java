package Dao.Class;

import android.database.Cursor;

import Dao.Db.User_Dao;

public class User {
    private Integer Id;
    private String Username;
    private String UserPrename;
    private String Type;
    private String Email;
    private String Password;


    public User(Cursor cursor) {
        if(cursor.moveToFirst()){
            do{
                Id = cursor.getInt(cursor.getColumnIndexOrThrow(User_Dao.Column_Id));
                Username = cursor.getString(cursor.getColumnIndexOrThrow(User_Dao.Column_USERNAME));
                UserPrename = cursor.getString(cursor.getColumnIndexOrThrow(User_Dao.Column_USERPRENAME));
                Type = cursor.getString(cursor.getColumnIndexOrThrow(User_Dao.Column_Type));;
                Email = cursor.getString(cursor.getColumnIndexOrThrow(User_Dao.Column_Email));;
                Password = cursor.getString(cursor.getColumnIndexOrThrow(User_Dao.Column_Password));;
            }while (cursor.moveToNext());
        }

    }

    public User(String username,String userPrename, String type, String email, String password) {
        Username = username;
        UserPrename = userPrename;
        Type = type;
        Email = email;
        Password = password;
    }
    public User(int id ,String username,String userPrename, String type, String email, String password) {
        Id = id;
        Username = username;
        UserPrename = userPrename;
        Type = type;
        Email = email;
        Password = password;
    }
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }


    public String getUserPrename() {
        return UserPrename;
    }

    public void setUserPrename(String userPrename) {
        UserPrename = userPrename;
    }
}
