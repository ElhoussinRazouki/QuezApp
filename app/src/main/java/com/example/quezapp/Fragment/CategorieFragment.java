package com.example.quezapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quezapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CategorieFragment extends Fragment {
    TabLayout tabLayout ;
    ViewPager2 viewPager;



    public CategorieFragment() {
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
        return inflater.inflate(R.layout.fragment_categorie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get Elements
        tabLayout = view.findViewById(R.id.frg_categorie_tab_layout);
        viewPager = view.findViewById(R.id.frg_categorie_view_pager);

        VPAdapter adapter = new VPAdapter(getActivity());
        adapter.addFragment(new SaisieCategorieFragment(),"Saisie");
        adapter.addFragment(new ViewCategorieFragment(),"View");

        // set adapter
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(adapter.getPageTitle(position));

            }
        }).attach();




    }
}