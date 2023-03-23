package com.example.quezapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quezapp.R;

import Dao.Class.Categorie;
import Dao.Class.Level;
import Dao.Db.Categorie_Dao;
import Dao.Db.Level_Dao;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SaisieLevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SaisieLevelFragment extends Fragment {
    //declare elements
    TextView txtlevel ;
    Button btnAdd;
    Level_Dao  level_dao;

    public SaisieLevelFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        level_dao = new Level_Dao(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saisie_level, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get elements
        txtlevel = view.findViewById(R.id.frg_level_txtCategorie);
        btnAdd = view.findViewById(R.id.frg_level_btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(level_dao.insert(new Level(txtlevel.getText().toString()))){
                    Toast.makeText(getContext(),"Level is Added",Toast.LENGTH_LONG).show();
                    txtlevel.setText("");
                    txtlevel.setFocusable(false);
                }
            }

        });

    }
}