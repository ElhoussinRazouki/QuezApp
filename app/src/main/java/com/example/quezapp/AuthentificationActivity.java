package com.example.quezapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quezapp.Fragment.LoginFragment;
import com.example.quezapp.Fragment.SignUpFragment;

import Dao.Class.User;
import Dao.Db.User_Dao;

public class AuthentificationActivity extends AppCompatActivity implements View.OnClickListener {
    LoginFragment loginFragment;
    SignUpFragment signUpFragment;
    EditText txtEmailLogin, txtPasswoordLogin ,txtEmailSignUp,txtpasswordSignUp,txtUserName;
    User_Dao user_dao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);
        txtEmailLogin = this.findViewById(R.id.txtEmail);
        txtPasswoordLogin = this.findViewById(R.id.txtPassword);
        txtEmailSignUp = this.findViewById(R.id.txtSignupEmail);
        txtpasswordSignUp = this.findViewById(R.id.txtSignUpPassword);
        txtUserName = this.findViewById(R.id.txtSignUpUserName);

        user_dao = new User_Dao(this);

        loginFragment = new LoginFragment();
        signUpFragment = new SignUpFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_AuthentificationActivity,loginFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.buttonFormSignUp:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_AuthentificationActivity,signUpFragment).commit();
                break;
            case R.id.btnLogin:
                txtEmailLogin = this.findViewById(R.id.txtEmail);
                txtPasswoordLogin = this.findViewById(R.id.txtPassword);
                if(txtEmailLogin.getText().toString().equals("admin")){
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
                else if (txtEmailLogin.getText().toString().equals("Hoho")){
                    Intent intent = new Intent(this,userActivity.class);
                    startActivity(intent);
                }
                break;
                //Cursor cursor = user_dao.login_User(txtEmailLogin.getText().toString(),txtPasswoordLogin.getText().toString());
                //if (cursor.getCount()>0)
                //{
                   // Bundle bundle = new Bundle();
                   // bundle.putString(User_Dao.Column_Email,txtEmailLogin.getText().toString());
                    //bundle.putString(User_Dao.Column_Password,txtPasswoordLogin.getText().toString());
                    // Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(this,MainActivity.class);
                    //intent.putExtra("user",bundle);
                    //startActivity(intent);
               // }

            case R.id.buttonFormLogin:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_AuthentificationActivity,loginFragment).commit();
                break;
            case R.id.btnSignUp:
               if( user_dao.Insert(new User(txtUserName.getText().toString(),txtUserName.getText().toString(),"admin",txtEmailSignUp.getText().toString(),txtpasswordSignUp.getText().toString()))){
                   Toast.makeText(this, "user is signed up", Toast.LENGTH_SHORT).show();
               }
                break;
        }

    }
}