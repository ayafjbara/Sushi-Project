package com.ux.itamae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class ExtrasFragmet extends Fragment {
    ExtrasPagerAdapter extrasPagerAdapter;
    ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.sushi_extras_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        extrasPagerAdapter = new ExtrasPagerAdapter(getChildFragmentManager());
        viewPager = view.findViewById(R.id.sushi_extras);
        viewPager.setAdapter(extrasPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}

class ExtrasPagerAdapter extends FragmentStatePagerAdapter {
    FishFragment fishFragment;
    VegetablesFragment vegetablesFragment;

    public ExtrasPagerAdapter(FragmentManager fm) {
        super(fm);
        fishFragment = new FishFragment();
        vegetablesFragment = new VegetablesFragment();
    }

    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                return fishFragment;
            case 1:
                return vegetablesFragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Fish";
            case 1:
                return "Vegetables";
        }
        return "";
    }
}

