package com.example.quezapp.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quezapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LevelFragment extends Fragment {

    TabLayout tabLayout ;
    ViewPager2 viewPager;



    public LevelFragment() {
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
        return inflater.inflate(R.layout.fragment_level, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //get Elements
        tabLayout = view.findViewById(R.id.frg_level_tab_layout);
        viewPager = view.findViewById(R.id.frg_level_view_pager);

        VPAdapter adapter = new VPAdapter(getActivity());
        adapter.addFragment(new SaisieLevelFragment(),"Saisie");
        adapter.addFragment(new ViewLevelFragment(),"View");

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