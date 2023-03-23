package com.example.quezapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.quezapp.R;

import Dao.Class.User;
import Dao.Db.User_Dao;


public class UserProfileFragment extends Fragment {
User_Dao user_dao ;
TextView txtuserdata;

    public UserProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtuserdata = view.findViewById(R.id.user_profile_user_name);
        user_dao = new User_Dao(getContext());
        User user = user_dao.getUser(getArguments().getInt(User_Dao.Column_Id));
        txtuserdata.setText(user.getUsername()+" "+user.getUserPrename());

    }
}