package com.example.quezapp.Fragment;

import android.content.Context;
import android.content.LocusId;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quezapp.R;
import com.example.quezapp.UserHistoryAdapter;
import com.example.quezapp.userActivity;

import Dao.Class.EvalClass;
import Dao.Db.Details_Evaluation_Dao;
import Dao.Db.Evaluation_Dao;
import Dao.Db.User_Dao;


public class UserHistoryFragment extends Fragment {

    int userId;
    Evaluation_Dao evaluation_dao;
    ListView listView;
    userActivity contextUser;

    public UserHistoryFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!=null){
            userId = getArguments().getInt(User_Dao.Column_Id);
        }

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        contextUser = (userActivity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = view.findViewById(R.id.listViewUserHistory);
        evaluation_dao = new Evaluation_Dao(getContext());
        Cursor cursor = evaluation_dao.getEvaluations(userId);
        UserHistoryAdapter userHistoryAdapter = new UserHistoryAdapter(getContext(),new EvalClass().getFromCursor(cursor));
        listView.setAdapter(userHistoryAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle bundle = new Bundle();
                bundle.putInt(Details_Evaluation_Dao.Column_ID_EVAL, (int) id);
                userDetailEvalFragment DetailEvalFragment = new userDetailEvalFragment();
                DetailEvalFragment.setArguments(bundle);
                contextUser.getSupportFragmentManager().beginTransaction().replace(R.id.Frame_Layout_MainActivity_user,DetailEvalFragment).commit();

            }
        });
    }
}