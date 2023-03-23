package Dao.Class;

import java.util.Date;

public class Evaluation {
    private Integer Id;
    private Integer Id_User;
    private Integer Id_Categorie;
    private Integer Id_Level;
    private Integer Score;
    private Date Date_Evaluation;

    public Evaluation(Integer id, Integer id_User, Integer id_Categorie,Integer id_Level, Date date_Evaluation, Integer score) {
        Id = id;
        Id_User = id_User;
        Id_Categorie = id_Categorie;
        Id_Level = id_Level;
        Score = score;
        Date_Evaluation = date_Evaluation;
    }

    public Evaluation(Integer id_User, Integer id_Categorie,Integer id_Level, Date date_Evaluation, Integer score) {
        Id_User = id_User;
        Id_Categorie = id_Categorie;
        Id_Level = id_Level;
        Score = score;
        Date_Evaluation = date_Evaluation;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public Integer getId_User() {
        return Id_User;
    }

    public void setId_User(Integer id_User) {
        Id_User = id_User;
    }

    public Integer getId_Category() {
        return Id_Categorie;
    }

    public void setId_Category(Integer id_Category) {
        Id_Categorie = id_Category;
    }

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer score) {
        Score = score;
    }

    public Date getDate_Exam() {
        return Date_Evaluation;
    }

    public void setDate_Exam(Date date_Exam) {
        Date_Evaluation = date_Exam;
    }

    public Integer getId_Level() {
        return Id_Level;
    }

    public void setId_Level(Integer id_Level) {
        Id_Level = id_Level;
    }
}
