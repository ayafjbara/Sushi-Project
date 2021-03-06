package com.ux.itamae;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class VegetablesFragment extends Fragment {

    SushiFillingsActivity sushiFillingsActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.extras_layout, container, false);
    }

    public void setSushiFillingsActivity(SushiFillingsActivity sushiFillingsActivity) {
        this.sushiFillingsActivity = sushiFillingsActivity;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.ic_ex_avocado);
        images.add(R.drawable.ic_ex_carrot);
        images.add(R.drawable.ic_ex_cucumber);
        GridView gridView = view.findViewById(R.id.extrasTable);
        gridView.setNumColumns(2);
        GridFillingAdapter gridExtraAdapter = new GridFillingAdapter(getContext(), R.layout.grid_image_view_layout, "", images);
        gridExtraAdapter.callBack = sushiFillingsActivity;
        gridView.setAdapter(gridExtraAdapter);
    }
}
