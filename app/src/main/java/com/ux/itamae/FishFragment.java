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

public class FishFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.extras_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        ArrayList<Integer> images = new ArrayList<>();
        images.add(R.drawable.ic_ex_redtuna);
        images.add(R.drawable.ic_ex_salmon);
        GridView gridView = view.findViewById(R.id.extrasTable);
        gridView.setNumColumns(2);
        GridExtraAdapter gridExtraAdapter = new GridExtraAdapter(getContext(), R.layout.grid_image_view_layout, "", images);
        gridView.setAdapter(gridExtraAdapter);
    }
}