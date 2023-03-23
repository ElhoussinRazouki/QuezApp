package com.example.quezapp.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.quezapp.MainActivity;
import com.example.quezapp.R;
import com.example.quezapp.userActivity;

import Dao.Class.User;
import Dao.Db.User_Dao;

public class LoginFragment extends Fragment {

    EditText txtEmail, txtPasswoord ;
    Switch switchPassword;
    Button btnLogin , btnFormToSignUp;
    User_Dao user_dao;
    User user;


    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        user_dao = new User_Dao(getContext());

        txtEmail = view.findViewById(R.id.txtEmail);
        txtPasswoord = view.findViewById(R.id.txtPassword);
        btnLogin = view.findViewById(R.id.btnLogin);
        btnFormToSignUp = view.findViewById(R.id.buttonFormSignUp);
        btnFormToSignUp.setOnClickListener((View.OnClickListener) getContext());
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = user_dao.login_User(txtEmail.getText().toString(),txtPasswoord.getText().toString());
                if(cursor.getCount()>0){
                    user = new User(cursor);
                    Intent intent = new Intent(getContext(),userActivity.class );
                    intent.putExtra(User_Dao.Column_Id,user.getId());
                    startActivity(intent);
                }
                else if (txtEmail.getText().toString().equals("admin")&&txtPasswoord.getText().toString().equals("admin")){
                    Intent intent = new Intent(getContext(),MainActivity.class );
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getContext(), "Email or Password is not Correct", Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchPassword = view.findViewById(R.id.switch_password_login);
        switchPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    txtPasswoord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    txtPasswoord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

    }
}