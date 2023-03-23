package com.example.quezapp.Fragment;

import androidx.annotation.NonNull;
import androidx.annotation.StyleableRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentStateAdapter {

    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    ArrayList<String> fragmentTitleArrayList = new ArrayList<>();

    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentArrayList.size();
    }
    public void addFragment(Fragment fragment , String fragmentTitle){
        fragmentArrayList.add(fragment);
        fragmentTitleArrayList.add(fragmentTitle);
    }
    public String getPageTitle(int position){
        return fragmentTitleArrayList.get(position);
    }
}
