package com.example.quezapp.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ContentFrameLayout;
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

import com.example.quezapp.R;

import Dao.Class.User;
import Dao.Db.User_Dao;

public class SignUpFragment extends Fragment {
    EditText txtEmail, txtPasswoord ,txtUserName,txtPreName;
    Switch switchPassword;
    Button btnSignUp , btnFormToLogin;
    User_Dao user_dao;
    Context acontext;


    public SignUpFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        acontext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        user_dao= new  User_Dao(acontext);
        txtEmail = view.findViewById(R.id.txtSignupEmail);
        txtPasswoord = view.findViewById(R.id.txtSignUpPassword);
        txtUserName = view.findViewById(R.id.txtSignUpUserName);
        txtPreName = view.findViewById(R.id.txtSignUpUserPreName);
        btnSignUp = view.findViewById(R.id.btnSignUp);
        btnFormToLogin = view.findViewById(R.id.buttonFormLogin);
        btnFormToLogin.setOnClickListener((View.OnClickListener) getContext());
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_dao.Insert(new User(txtUserName.getText().toString(),txtPreName.getText().toString(),"user",txtEmail.getText().toString(),txtPasswoord.getText().toString()))){
                    Toast.makeText(acontext, "successfuly Sign Up", Toast.LENGTH_SHORT).show();
                }
            }
        });
        switchPassword = view.findViewById(R.id.switch_password_signup);
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