package com.ux.itamae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class FillingsFragment extends Fragment {
    private FillingsPagerAdapter fillingsPagerAdapter;
    private ViewPager viewPager;
    private SushiFillingsActivity sushiFillingsActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sushi_fillings_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fillingsPagerAdapter = new FillingsPagerAdapter(getChildFragmentManager(), sushiFillingsActivity);
        viewPager = view.findViewById(R.id.sushi_fillings);
        viewPager.setAdapter(fillingsPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    public void setSushiFillingsActivity(SushiFillingsActivity sushiFillingsActivity) {
        this.sushiFillingsActivity = sushiFillingsActivity;
    }
}

class FillingsPagerAdapter extends FragmentStatePagerAdapter {
    private FishFragment fishFragment;
    private VegetablesFragment vegetablesFragment;

    public FillingsPagerAdapter(FragmentManager fm, SushiFillingsActivity sushiFillingsActivity) {
        super(fm);
        fishFragment = new FishFragment();
        fishFragment.setSushiFillingsActivity(sushiFillingsActivity);

        vegetablesFragment = new VegetablesFragment();
        vegetablesFragment.setSushiFillingsActivity(sushiFillingsActivity);
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0)
            return fishFragment;
        else
            return vegetablesFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0)
            return "Fish";
        else
            return "Vegetables";
    }
}

