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

import com.example.quezapp.MainActivity;
import com.example.quezapp.R;

import org.w3c.dom.Text;

import Dao.Class.Categorie;
import Dao.Db.Categorie_Dao;


public class SaisieCategorieFragment extends Fragment {

    //declare elements
   TextView txtCategorie ;
   Button btnAdd;
   Categorie_Dao categorie_dao;

    public SaisieCategorieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        categorie_dao = new Categorie_Dao(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saisie_categorie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get elements
        txtCategorie = view.findViewById(R.id.frg_categorie_txtCategorie);
        btnAdd = view.findViewById(R.id.frg_category_btn_add);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(categorie_dao.insert(new Categorie(txtCategorie.getText().toString()))){
                    Toast.makeText(getContext(),"Categorie is Added",Toast.LENGTH_SHORT).show();
                    txtCategorie.setText("");
                }
            }

        });

    }
}